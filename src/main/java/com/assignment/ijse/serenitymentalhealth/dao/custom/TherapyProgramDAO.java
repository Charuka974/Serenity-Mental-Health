package com.assignment.ijse.serenitymentalhealth.dao.custom;

import com.assignment.ijse.serenitymentalhealth.dao.CrudDAO;
import com.assignment.ijse.serenitymentalhealth.entity.Patient;
import com.assignment.ijse.serenitymentalhealth.entity.PatientProgram;
import com.assignment.ijse.serenitymentalhealth.entity.TherapyProgram;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface TherapyProgramDAO extends CrudDAO<TherapyProgram> {

//    public boolean save(TherapyProgram entity);
//    public boolean update(TherapyProgram entity);
//    public boolean delete(String pk);
//    public List<TherapyProgram> getAll();
    public List<TherapyProgram> findByTherapyProgramName(String name);
    public Optional<TherapyProgram> findById(String pk);
//    public Optional<String> getLastPK();

}
