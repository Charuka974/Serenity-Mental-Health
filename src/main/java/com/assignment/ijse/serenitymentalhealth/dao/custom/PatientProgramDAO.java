package com.assignment.ijse.serenitymentalhealth.dao.custom;

import com.assignment.ijse.serenitymentalhealth.dao.CrudDAO;
import com.assignment.ijse.serenitymentalhealth.entity.Patient;
import com.assignment.ijse.serenitymentalhealth.entity.PatientProgram;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PatientProgramDAO extends CrudDAO<PatientProgram> {

//    public boolean save(PatientProgram entity);
//    public boolean update(PatientProgram entity);
    public boolean delete(String patientId, String programId);
//    public List<PatientProgram> getAll();
    public List<PatientProgram> searchByName(String name);
//    public Optional<String> getLastPK();
    public List<PatientProgram> findByPatientId(String id);
    public List<PatientProgram> findByProgramId(String id);

    public Optional<PatientProgram> findById(String patientId, String programId);
    public boolean updateTherapyProgramFee(String patientId, String programId, BigDecimal newFee);


}
