package com.assignment.ijse.serenitymentalhealth.dao.custom.impl;

import com.assignment.ijse.serenitymentalhealth.config.FactoryConfiguration;
import com.assignment.ijse.serenitymentalhealth.dao.custom.UserDAO;
import com.assignment.ijse.serenitymentalhealth.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(User entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(User entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(String pk) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.find(User.class, pk);
            // User user = session.get(User.class, pk);
            if (user!= null) {
                session.remove(user);
                transaction.commit();
                return true;
            }
            return false;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAll() {
        Session session = factoryConfiguration.getSession();
        List<User> users = session.createQuery("FROM users", User.class).list();
        session.close();
        return users;

    }

    @Override
    public Optional<User> findByName(String pk) {
        Session session = factoryConfiguration.getSession();
        User user = session.get(User.class, pk);
        session.close();
//        if (user == null){
//            return Optional.empty();
//        }
//        return Optional.of(user);
        return Optional.ofNullable(user);

    }

    @Override
    public Optional<String> getLastPK() {
        Session session = factoryConfiguration.getSession();
        String lastPk = session.createQuery("SELECT u.userId FROM users u ORDER BY u.userId DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();
        session.close();

        return Optional.ofNullable(lastPk);
    }

    @Override
    public String validateUser(String username, String password) {
        Session session = factoryConfiguration.getSession();
        Object[] result = null;

        try {
            result = session.createQuery(
                            "SELECT u.password, u.role FROM User u WHERE u.username = :username", Object[].class)
                    .setParameter("username", username)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        if (result == null) {
            System.out.println("Debug: No user found with username: " + username);
            return null;
        }

        String databasePassword = (String) result[0];
        String role = (String) result[1];

        if (databasePassword != null && databasePassword.equals(password)) {
            System.out.println("Debug: Authentication successful for role: " + role);
            return role;
        } else {
            System.out.println("Debug: Password does not match for user: " + username);
            return null;
        }
    }





}
