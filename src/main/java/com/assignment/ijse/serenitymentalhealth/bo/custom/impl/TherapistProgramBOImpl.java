package com.assignment.ijse.serenitymentalhealth.bo.custom.impl;

import com.assignment.ijse.serenitymentalhealth.bo.custom.TherapistProgramBO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.TherapistDAO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.TherapistProgramDAO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.TherapyProgramDAO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.impl.TherapistDAOImpl;
import com.assignment.ijse.serenitymentalhealth.dao.custom.impl.TherapistProgramDAOImpl;
import com.assignment.ijse.serenitymentalhealth.dao.custom.impl.TherapyProgramDAOImpl;
import com.assignment.ijse.serenitymentalhealth.dto.TherapistProgramDto;
import com.assignment.ijse.serenitymentalhealth.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TherapistProgramBOImpl implements TherapistProgramBO {

    private TherapistProgramDAO therapistProgramDAO = new TherapistProgramDAOImpl();
    private TherapistDAO therapistDAO = new TherapistDAOImpl();
    private TherapyProgramDAO therapyProgramDAO = new TherapyProgramDAOImpl();

    @Override
    public boolean saveTherapistProgram(String therapistId, String programId) {
        Optional<Therapist> therapistOtp = therapistDAO.findById(therapistId);
        Optional<TherapyProgram> programOpt = therapyProgramDAO.findById(programId);

        if (therapistOtp.isEmpty() || programOpt.isEmpty()) {
            return false;
        }
        if (therapistProgramDAO.findById(therapistId, programId).isPresent()) {
            return false;
        }
        Therapist therapist = therapistOtp.get();
        TherapyProgram program = programOpt.get();

        TherapistProgram therapistProgram = new TherapistProgram();
        therapistProgram.setId(new TherapistProgramId(therapistId, programId));
        therapistProgram.setTherapist(therapist);
        therapistProgram.setTherapy_program(program);

        return therapistProgramDAO.save(therapistProgram);
    }

    @Override
    public boolean updateTherapistProgram(String therapistId, String programId) {
        Optional<Therapist> therapistOpt = therapistDAO.findById(therapistId);
        Optional<TherapyProgram> programOpt = therapyProgramDAO.findById(programId);

        if (therapistOpt.isEmpty() || programOpt.isEmpty()) {
            return false;
        }

        Therapist therapist = therapistOpt.get();
        TherapyProgram therapyProgram = programOpt.get();

        TherapistProgram entity = new TherapistProgram(
                new TherapistProgramId(therapistId, programId),
                therapist,
                therapyProgram
        );

        return therapistProgramDAO.update(entity);
    }

    @Override
    public boolean deleteTherapistProgram(String therapistId, String programId) {
        return therapistProgramDAO.delete(therapistId, programId);
    }

    @Override
    public TherapistProgramDto findById(String therapistId, String programId) {
        Optional<TherapistProgram> result = therapistProgramDAO.findById(therapistId, programId);
        if (result.isPresent()) {
            TherapistProgram entity = result.get();
            return new TherapistProgramDto(
                    entity.getId().getTherapistId(),
                    entity.getId().getProgramId(),
                    entity.getTherapy_program().getName()
            );
        } else {
            return null;
        }
    }

    @Override
    public List<TherapistProgramDto> getAllTherapistPrograms() {
//        List<TherapistProgram> allPrograms = therapistProgramDAO.getAll();
//
//        return allPrograms.stream()
//                .map(entity -> new TherapistProgramDto(
//                        entity.getId().getTherapistId(),
//                        entity.getId().getProgramId(),
//                        entity.getTherapist().getName()
//                ))
//                .collect(Collectors.toList());

        List<TherapistProgram> programs = therapistProgramDAO.getAll();
        List<TherapistProgramDto> dtos = new ArrayList<>();

        for (TherapistProgram entity : programs) {
            TherapistProgramDto dto = new TherapistProgramDto(
                    entity.getId().getTherapistId(),
                    entity.getId().getProgramId(),
                    entity.getTherapy_program().getName()
            );
            dtos.add(dto);
        }

        return dtos;

    }

    @Override
    public List<TherapistProgramDto> getTherapistProgramsByTherapist(String id) {

        List<TherapistProgram> programs = therapistProgramDAO.findByTherapist(id);
        List<TherapistProgramDto> dtos = new ArrayList<>();

        for (TherapistProgram entity : programs) {
            TherapistProgramDto dto = new TherapistProgramDto(
                    entity.getId().getTherapistId(),
                    entity.getId().getProgramId(),
                    entity.getTherapy_program().getName()
            );
            dtos.add(dto);
        }

        return dtos;

    }

    @Override
    public List<TherapistProgramDto> findByProgramName(String name) {
        List<TherapistProgram> programs = therapistProgramDAO.findByProgramName(name);
        List<TherapistProgramDto> dtos = new ArrayList<>();

        for (TherapistProgram entity : programs) {
            TherapistProgramDto dto = new TherapistProgramDto(
                    entity.getId().getTherapistId(),
                    entity.getId().getProgramId(),
                    entity.getTherapy_program().getName()
            );
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public List<TherapistProgramDto> getTherapistProgramsByTherapistId(String therapistId) {
        List<TherapistProgram> programs = therapistProgramDAO.findByTherapistId(therapistId);
        List<TherapistProgramDto> dtos = new ArrayList<>();

        for (TherapistProgram entity : programs) {
            TherapistProgramDto dto = new TherapistProgramDto(
                    entity.getId().getTherapistId(),
                    entity.getId().getProgramId(),
                    entity.getTherapy_program().getName()
            );
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public List<TherapistProgramDto> getTherapistProgramsByProgramId(String programId) {
        List<TherapistProgram> programs = therapistProgramDAO.findByProgramId(programId);
        List<TherapistProgramDto> dtos = new ArrayList<>();

        for (TherapistProgram entity : programs) {
            TherapistProgramDto dto = new TherapistProgramDto(
                    entity.getId().getTherapistId(),
                    entity.getId().getProgramId(),
                    entity.getTherapy_program().getName()
            );
            dtos.add(dto);
        }

        return dtos;
    }



}
