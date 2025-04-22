package com.assignment.ijse.serenitymentalhealth.dao.custom;

import com.assignment.ijse.serenitymentalhealth.config.FactoryConfiguration;
import com.assignment.ijse.serenitymentalhealth.dao.CrudDAO;
import com.assignment.ijse.serenitymentalhealth.entity.Therapist;
import com.assignment.ijse.serenitymentalhealth.entity.TherapistAvailability;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TherapistAvailabiltyDAO extends CrudDAO<TherapistAvailability> {

//    public List<TherapistAvailability> getAll();
//    public boolean save(TherapistAvailability entity);
//    public boolean update(TherapistAvailability entity);
//    public boolean delete(String availabilityId);
    public List<TherapistAvailability> findByTherapistAndDate(String therapistId, LocalDate date);
    public List<TherapistAvailability> findByTherapistId(String therapistId);
    public List<TherapistAvailability> findByDate(LocalDate date);
//    public Optional<String> getLastPK();


}
