package com.assignment.ijse.serenitymentalhealth.dao.custom;

import com.assignment.ijse.serenitymentalhealth.entity.Patient;
import com.assignment.ijse.serenitymentalhealth.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentDAO {
    public boolean save(Payment entity);
    public boolean update(Payment entity);
    public boolean delete(String pk);
    public List<Payment> getAll();
    public List<Payment> findByName(String name);
    public Optional<Payment> findById(String pk);
    public Optional<String> getLastPK();
}
