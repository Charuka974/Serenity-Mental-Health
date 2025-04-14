package com.assignment.ijse.serenitymentalhealth.bo.custom;

import com.assignment.ijse.serenitymentalhealth.dto.PatientDto;
import com.assignment.ijse.serenitymentalhealth.dto.PatientProgramDto;
import com.assignment.ijse.serenitymentalhealth.dto.TherapyProgramDto;

import java.util.ArrayList;

public interface PatientProgramBO {

    public boolean savePatientProgram(PatientProgramDto dto);
    public boolean updatePatientProgram(PatientProgramDto dto);
    public boolean deletePatientProgram(String patientName, String programName);
    public ArrayList<PatientProgramDto> getAllPatientPrograms();
    public PatientDto findByPatientName(String patientName);
    public TherapyProgramDto findByProgramName(String programName);
    public ArrayList<PatientProgramDto> search(String name, boolean isPatient);
    public String getNextPatientProgramPK();
}
