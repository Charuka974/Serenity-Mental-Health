package com.assignment.ijse.serenitymentalhealth.dao.custom;


import com.assignment.ijse.serenitymentalhealth.dao.CrudDAO;
import com.assignment.ijse.serenitymentalhealth.dao.SuperDAO;
import com.assignment.ijse.serenitymentalhealth.entity.Therapist;
import org.hibernate.Session;


import java.util.List;
import java.util.Optional;

public interface TherapistDAO{

    public boolean save(Therapist entity);
    public boolean update(Therapist entity);
    public boolean delete(String pk);
    public List<Therapist> getAll();
    public List<Therapist> findByName(String name);
    public Optional<String> getLastPK();
    public Optional<Therapist> findById(String id);

}
