package com.assignment.ijse.serenitymentalhealth.dao.custom;

import com.assignment.ijse.serenitymentalhealth.dao.CrudDAO;
import com.assignment.ijse.serenitymentalhealth.entity.PatientProgram;
import com.assignment.ijse.serenitymentalhealth.entity.TherapySession;

import java.util.List;
import java.util.Optional;

public interface TherapySessionDAO extends CrudDAO<TherapySession> {

//    public boolean save(TherapySession entity);
//    public boolean update(TherapySession entity);
//    public boolean delete(String pk);
//    public List<TherapySession> getAll();
//    public Optional<String> getLastPK();
    Optional<TherapySession> findBySessionId(String sessionId);
    List<TherapySession> findByPatientId(String patientId);


}
