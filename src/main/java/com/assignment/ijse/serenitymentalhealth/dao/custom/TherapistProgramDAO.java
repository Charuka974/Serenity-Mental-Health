package com.assignment.ijse.serenitymentalhealth.dao.custom;

import com.assignment.ijse.serenitymentalhealth.dao.CrudDAO;
import com.assignment.ijse.serenitymentalhealth.entity.TherapistProgram;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface TherapistProgramDAO extends CrudDAO<TherapistProgram> {
//    boolean save(TherapistProgram entity);
//    boolean update(TherapistProgram entity);
    boolean delete(String therapistId, String programId);
//    List<TherapistProgram> getAll();
    List<TherapistProgram> findByProgramName(String name);
    List<TherapistProgram> findByTherapist(String name);
    Optional<TherapistProgram> findById(String therapistId, String programId);
//    Optional<String> getLastPK();

    public List<TherapistProgram> findByTherapistId(String therapistId);
    public List<TherapistProgram> findByProgramId(String programId);

}
