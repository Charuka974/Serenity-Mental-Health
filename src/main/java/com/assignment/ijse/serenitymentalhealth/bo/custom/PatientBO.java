package com.assignment.ijse.serenitymentalhealth.bo.custom;

import com.assignment.ijse.serenitymentalhealth.bo.SuperBO;
import com.assignment.ijse.serenitymentalhealth.dto.PatientDto;

import java.util.ArrayList;
import java.util.Optional;


public interface PatientBO extends SuperBO {

    public boolean savePatient(PatientDto dto);
    public boolean updatePatient(PatientDto dto);
    public boolean deletePatient(String pk);
    public ArrayList<PatientDto> getAllPatients();
    public ArrayList<PatientDto> findByPatientName(String name);
    public String getNextPatientPK();


}
