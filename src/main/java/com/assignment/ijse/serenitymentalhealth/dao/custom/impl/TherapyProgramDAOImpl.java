package com.assignment.ijse.serenitymentalhealth.dao.custom.impl;

import com.assignment.ijse.serenitymentalhealth.config.FactoryConfiguration;
import com.assignment.ijse.serenitymentalhealth.dao.custom.TherapyProgramDAO;
import com.assignment.ijse.serenitymentalhealth.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(TherapyProgram entity) {
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
    public boolean update(TherapyProgram entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
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
            TherapyProgram program = session.find(TherapyProgram.class, pk);
            // TherapyProgram program = session.get(TherapyProgram.class, pk);
            if (program!= null) {
                session.remove(program);
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
    public List<TherapyProgram> getAll() {
        Session session = factoryConfiguration.getSession();
        List<TherapyProgram> programs = session.createQuery("FROM TherapyProgram", TherapyProgram.class).list();
        session.close();
        return programs;
    }

    @Override
    public List<TherapyProgram> findByName(String name) {
        Session session = factoryConfiguration.getSession();
        List<TherapyProgram> programs = session.createQuery("FROM TherapyProgram tp WHERE tp.name LIKE :name", TherapyProgram.class)
                .setParameter("name", "%" + name + "%")
                .list();
        session.close();

        return programs;
    }


    @Override
    public Optional<String> getLastPK() {
        Session session = factoryConfiguration.getSession();
        String lastPk = session.createQuery("SELECT tp.programId FROM TherapyProgram tp ORDER BY tp.programId DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();
        session.close();

        return Optional.ofNullable(lastPk);
    }


}
