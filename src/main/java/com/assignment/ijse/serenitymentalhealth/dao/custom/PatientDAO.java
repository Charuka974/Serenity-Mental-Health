package com.assignment.ijse.serenitymentalhealth.dao.custom;

import com.assignment.ijse.serenitymentalhealth.dao.SuperDAO;
import com.assignment.ijse.serenitymentalhealth.dto.PatientDto;
import com.assignment.ijse.serenitymentalhealth.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PatientDAO extends SuperDAO {

    public boolean save(Patient entity);
    public boolean update(Patient entity);
    public boolean delete(String pk);
    public List<Patient> getAll();
    public List<Patient> findByName(String name);
    public Optional<String> getLastPK();

}
