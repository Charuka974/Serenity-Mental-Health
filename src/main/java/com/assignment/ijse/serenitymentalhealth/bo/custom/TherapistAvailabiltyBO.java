package com.assignment.ijse.serenitymentalhealth.bo.custom;

import com.assignment.ijse.serenitymentalhealth.bo.SuperBO;
import com.assignment.ijse.serenitymentalhealth.dto.TherapistAvailabilityDto;
import com.assignment.ijse.serenitymentalhealth.entity.Therapist;
import com.assignment.ijse.serenitymentalhealth.entity.TherapistAvailability;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TherapistAvailabiltyBO extends SuperBO {

    public boolean saveTherapistAvailability(TherapistAvailabilityDto dto);
    public boolean updateTherapistAvailability(TherapistAvailabilityDto dto);
    public boolean deleteAvailability(String availabilityId);
    public List<TherapistAvailabilityDto> getAllAvailability();
    public List<TherapistAvailabilityDto> findByTherapistId(String therapistId);
    public List<TherapistAvailabilityDto> findByDate(LocalDate date);
    public List<TherapistAvailabilityDto> findByTherapistAndDate(String therapistId, LocalDate date);
    public boolean bookTimeSlot(String therapistId, LocalDate date, LocalTime startTime, Duration sessionDuration);
    public String generateNextId();

    public boolean restoreTimeSlot(String therapistId, LocalDate date, LocalTime startTime, Duration sessionDuration);

}

