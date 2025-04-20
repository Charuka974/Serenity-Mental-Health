package com.assignment.ijse.serenitymentalhealth.bo.custom;

import com.assignment.ijse.serenitymentalhealth.dto.TherapistAvailabilityDto;
import com.assignment.ijse.serenitymentalhealth.entity.Therapist;
import com.assignment.ijse.serenitymentalhealth.entity.TherapistAvailability;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TherapistAvailabiltyBO {
    public boolean saveAvailability(TherapistAvailabilityDto dto);
    public boolean updateAvailability(TherapistAvailabilityDto dto);
    public boolean deleteAvailability(String pk);
    public ArrayList<TherapistAvailabilityDto> getAllAvailabilities();
    public ArrayList<TherapistAvailabilityDto> findByTherapist(String name);
    public String getNextPK();

    public ArrayList<TherapistAvailabilityDto> findAvailableSlotsByTherapist(String therapistId);
    public boolean splitAvailabilitySlot(String therapistId, LocalDate date, LocalTime sessionStart, int sessionDuration);
    public TherapistAvailability findSlotContaining(String therapistId, LocalDate date, LocalTime sessionStart);


}

