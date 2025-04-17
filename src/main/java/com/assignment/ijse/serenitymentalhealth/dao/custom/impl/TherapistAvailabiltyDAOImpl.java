package com.assignment.ijse.serenitymentalhealth.dao.custom.impl;

import com.assignment.ijse.serenitymentalhealth.config.FactoryConfiguration;
import com.assignment.ijse.serenitymentalhealth.dao.custom.TherapistAvailabiltyDAO;
import com.assignment.ijse.serenitymentalhealth.entity.Therapist;
import com.assignment.ijse.serenitymentalhealth.entity.TherapistAvailability;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class TherapistAvailabiltyDAOImpl implements TherapistAvailabiltyDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(TherapistAvailability entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(TherapistAvailability entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String pk) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            TherapistAvailability therapistAvailability = session.find(TherapistAvailability.class, pk);
            if (therapistAvailability != null) {
                session.remove(therapistAvailability);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<TherapistAvailability> getAll() {
        Session session = factoryConfiguration.getSession();
        try {
            return session.createQuery("FROM TherapistAvailability", TherapistAvailability.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<TherapistAvailability> findByName(String name) {
        // does not have a name field
        return null;
    }

    @Override
    public List<TherapistAvailability> findByTherapist(String therapistName) {
        Session session = factoryConfiguration.getSession();
        try {
            return session.createQuery(
                            "FROM TherapistAvailability ta WHERE ta.therapist.name LIKE :name", TherapistAvailability.class)
                    .setParameter("name", "%" + therapistName + "%")
                    .list();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = factoryConfiguration.getSession();
        try {
            String lastPk = session.createQuery(
                            "SELECT ta.availability_id FROM TherapistAvailability ta ORDER BY ta.availability_id DESC",
                            String.class)
                    .setMaxResults(1)
                    .uniqueResult();
            return Optional.ofNullable(lastPk);
        } finally {
            session.close();
        }
    }

}
