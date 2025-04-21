package com.assignment.ijse.serenitymentalhealth.bo.custom;

import com.assignment.ijse.serenitymentalhealth.bo.SuperBO;
import com.assignment.ijse.serenitymentalhealth.dto.PatientDto;
import com.assignment.ijse.serenitymentalhealth.dto.PatientProgramDto;
import com.assignment.ijse.serenitymentalhealth.dto.TherapyProgramDto;
import com.assignment.ijse.serenitymentalhealth.entity.PatientProgram;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PatientProgramBO extends SuperBO {

    public boolean savePatientProgram(PatientProgramDto dto);
    public boolean updatePatientProgram(PatientProgramDto dto);
    public boolean deletePatientProgram(String patientName, String programName);
    public ArrayList<PatientProgramDto> getAllPatientPrograms();
    public PatientDto findByPatientName(String patientName);
    public TherapyProgramDto findByProgramName(String programName);
    public ArrayList<PatientProgramDto> search(String name, boolean isPatient);
    public String getNextPatientProgramPK();

    public List<PatientProgramDto> getProgramsByPatientId(String patientId);
    public List<PatientProgramDto> getPatientsByProgramId(String programId);

    public boolean updateTherapyProgramFeeOfPatient(String patientId, String programId, BigDecimal newFee);
    public PatientProgramDto searchPatientProgramFromBothIds(String patientId, String programId);

}
