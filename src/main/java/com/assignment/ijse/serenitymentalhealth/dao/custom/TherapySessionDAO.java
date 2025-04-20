package com.assignment.ijse.serenitymentalhealth.dao.custom;

import com.assignment.ijse.serenitymentalhealth.entity.TherapySession;

import java.util.List;
import java.util.Optional;

public interface TherapySessionDAO {

    public boolean save(TherapySession entity);
    public boolean update(TherapySession entity);
    public boolean delete(String pk);
    public List<TherapySession> getAll();
    public Optional<TherapySession> findById(String pk);
    public Optional<String> getLastPK();

}
