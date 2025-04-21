package com.assignment.ijse.serenitymentalhealth.bo.custom;

import com.assignment.ijse.serenitymentalhealth.bo.SuperBO;
import com.assignment.ijse.serenitymentalhealth.dto.TherapyProgramDto;
import com.assignment.ijse.serenitymentalhealth.dto.TherapySessionDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TherapySessionBO extends SuperBO {
    boolean save(TherapySessionDto dto);
    boolean update(TherapySessionDto dto);
    boolean delete(String sessionId);
    List<TherapySessionDto> getAll();
    TherapySessionDto findBySessionId(String sessionId);
    List<TherapySessionDto> findByPatientId(String patientId);
    String getNextSessionPK();

}
