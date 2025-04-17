package com.assignment.ijse.serenitymentalhealth.dao.custom;

import com.assignment.ijse.serenitymentalhealth.entity.Therapist;
import com.assignment.ijse.serenitymentalhealth.entity.TherapistAvailability;

import java.util.List;
import java.util.Optional;

public interface TherapistAvailabiltyDAO {
    public boolean save(TherapistAvailability entity);
    public boolean update(TherapistAvailability entity);
    public boolean delete(String pk);
    public List<TherapistAvailability> getAll();
    public List<TherapistAvailability> findByName(String name);
    public Optional<String> getLastPK();
    public List<TherapistAvailability> findByTherapist(String therapistName);

}
