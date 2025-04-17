package com.assignment.ijse.serenitymentalhealth.bo.custom;

import com.assignment.ijse.serenitymentalhealth.dto.TherapistAvailabilityDto;

import java.util.ArrayList;

public interface TherapistAvailabiltyBO {
    public boolean saveAvailability(TherapistAvailabilityDto dto);
    public boolean updateAvailability(TherapistAvailabilityDto dto);
    public boolean deleteAvailability(String pk);
    public ArrayList<TherapistAvailabilityDto> getAllAvailabilities();
    public ArrayList<TherapistAvailabilityDto> findByTherapist(String name);
    public String getNextPK();
}

