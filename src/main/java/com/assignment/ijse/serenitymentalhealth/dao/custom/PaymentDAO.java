package com.assignment.ijse.serenitymentalhealth.dao.custom;

import com.assignment.ijse.serenitymentalhealth.dao.CrudDAO;
import com.assignment.ijse.serenitymentalhealth.entity.Patient;
import com.assignment.ijse.serenitymentalhealth.entity.PatientProgram;
import com.assignment.ijse.serenitymentalhealth.entity.Payment;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PaymentDAO extends CrudDAO<Payment> {
//    public boolean save(Payment entity);
//    public boolean update(Payment entity);
//    public boolean delete(String pk);
//    public List<Payment> getAll();
    public List<Payment> findByPatientName(String name);
    public Optional<Payment> findById(String pk);
//    public Optional<String> getLastPK();
    public List<Payment> findByDate(LocalDate date);

}
