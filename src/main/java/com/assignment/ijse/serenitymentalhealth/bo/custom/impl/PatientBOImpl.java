package com.assignment.ijse.serenitymentalhealth.bo.custom.impl;

import com.assignment.ijse.serenitymentalhealth.bo.custom.PatientBO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.PatientDAO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.impl.PatientDAOImpl;
import com.assignment.ijse.serenitymentalhealth.dto.PatientDto;
import com.assignment.ijse.serenitymentalhealth.entity.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PatientBOImpl implements PatientBO {
    PatientDAO patientDAO = new PatientDAOImpl();

    @Override
    public boolean savePatient(PatientDto dto) {
        Patient patient = new Patient();
        patient.setPatientId(dto.getPatientId());
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setPhone(dto.getPhone());
        patient.setAddress(dto.getAddress());
        patient.setMedicalHistory(dto.getMedicalHistory());

        return patientDAO.save(patient);
    }

    @Override
    public boolean updatePatient(PatientDto dto) {
        Patient patient = new Patient();
        patient.setPatientId(dto.getPatientId());
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setPhone(dto.getPhone());
        patient.setAddress(dto.getAddress());
        patient.setMedicalHistory(dto.getMedicalHistory());

        return patientDAO.update(patient);
    }


    @Override
    public boolean deletePatient(String pk) {
        return patientDAO.delete(pk);
    }

    @Override
    public ArrayList<PatientDto> getAllPatients() {
        List<Patient> patients = patientDAO.getAll();

        ArrayList<PatientDto> patientDtos = new ArrayList<>();
        for (Patient patient : patients) {
            PatientDto patientDto = new PatientDto();
            patientDto.setPatientId(patient.getPatientId());
            patientDto.setName(patient.getName());
            patientDto.setEmail(patient.getEmail());
            patientDto.setPhone(patient.getPhone());
            patientDto.setAddress(patient.getAddress());
            patientDto.setMedicalHistory(patient.getMedicalHistory());
            patientDtos.add(patientDto);
        }
        return patientDtos;
    }



    @Override
    public ArrayList<PatientDto> findByPatientName(String name) {
        List<Patient> patients = patientDAO.findByName(name);
        ArrayList<PatientDto> patientDtos = new ArrayList<>();

        for (Patient patient : patients) {
            PatientDto patientDto = new PatientDto();
            patientDto.setPatientId(patient.getPatientId());
            patientDto.setName(patient.getName());
            patientDto.setEmail(patient.getEmail());
            patientDto.setPhone(patient.getPhone());
            patientDto.setAddress(patient.getAddress());
            patientDto.setMedicalHistory(patient.getMedicalHistory());
            patientDtos.add(patientDto);
        }

        return patientDtos;
    }



    @Override
    public String getNextPatientPK() {
        Optional<String> lastPkOpt = patientDAO.getLastPK();

        if (lastPkOpt.isPresent()) {
            String lastPk = lastPkOpt.get();
            String numericPart = lastPk.substring(1);
            int currentId = Integer.parseInt(numericPart);
            int nextId = currentId + 1;

            return String.format("P%03d", nextId);
        }

        return "P001";
    }



}
