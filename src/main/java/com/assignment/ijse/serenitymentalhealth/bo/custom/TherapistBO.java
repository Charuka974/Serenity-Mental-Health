package com.assignment.ijse.serenitymentalhealth.bo.custom;

import com.assignment.ijse.serenitymentalhealth.bo.SuperBO;
import com.assignment.ijse.serenitymentalhealth.dto.TherapistDto;
import com.assignment.ijse.serenitymentalhealth.entity.Therapist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TherapistBO  extends SuperBO {
    public boolean saveTherapist(TherapistDto dto);
    public boolean updateTherapist(TherapistDto dto);
    public boolean deleteTherapist(String pk);
    public ArrayList<TherapistDto> getAllTherapists();
    public ArrayList<TherapistDto> findByTherapistName(String name);
    public String getNextTherapistPK();
}
