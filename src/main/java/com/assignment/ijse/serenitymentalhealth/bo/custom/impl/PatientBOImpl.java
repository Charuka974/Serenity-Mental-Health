package com.assignment.ijse.serenitymentalhealth.bo.custom.impl;

import com.assignment.ijse.serenitymentalhealth.bo.custom.PatientBO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.PatientDAO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.impl.PatientDAOImpl;
import com.assignment.ijse.serenitymentalhealth.dto.PatientDto;
import com.assignment.ijse.serenitymentalhealth.dto.TherapyProgramDto;
import com.assignment.ijse.serenitymentalhealth.entity.Patient;
import com.assignment.ijse.serenitymentalhealth.entity.TherapyProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PatientBOImpl implements PatientBO {
    PatientDAO patientDAO = new PatientDAOImpl();

    @Override
    public boolean savePatient(PatientDto dto) {
        Patient patient = new Patient();
        patient.setPatient_id(dto.getPatientId());
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setPhone(dto.getPhone());
        patient.setAddress(dto.getAddress());
        patient.setMedical_history(dto.getMedicalHistory());

        return patientDAO.save(patient);
    }

    @Override
    public boolean updatePatient(PatientDto dto) {
        Patient patient = new Patient();
        patient.setPatient_id(dto.getPatientId());
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setPhone(dto.getPhone());
        patient.setAddress(dto.getAddress());
        patient.setMedical_history(dto.getMedicalHistory());

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
            patientDto.setPatientId(patient.getPatient_id());
            patientDto.setName(patient.getName());
            patientDto.setEmail(patient.getEmail());
            patientDto.setPhone(patient.getPhone());
            patientDto.setAddress(patient.getAddress());
            patientDto.setMedicalHistory(patient.getMedical_history());
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
            patientDto.setPatientId(patient.getPatient_id());
            patientDto.setName(patient.getName());
            patientDto.setEmail(patient.getEmail());
            patientDto.setPhone(patient.getPhone());
            patientDto.setAddress(patient.getAddress());
            patientDto.setMedicalHistory(patient.getMedical_history());
            patientDtos.add(patientDto);
        }

        return patientDtos;
    }

    @Override
    public PatientDto findPatientByID(String id) {
        Optional<Patient> patientOpt = patientDAO.findById(id);

        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();
            return new PatientDto(
                    patient.getPatient_id(),
                    patient.getName(),
                    patient.getEmail(),
                    patient.getPhone(),
                    patient.getAddress(),
                    patient.getMedical_history()
            );
        }

        return null;
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
