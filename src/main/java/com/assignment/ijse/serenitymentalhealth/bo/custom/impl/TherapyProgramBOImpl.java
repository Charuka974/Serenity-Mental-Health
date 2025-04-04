package com.assignment.ijse.serenitymentalhealth.bo.custom.impl;

import com.assignment.ijse.serenitymentalhealth.bo.custom.TherapyProgramBO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.TherapyProgramDAO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.impl.TherapyProgramDAOImpl;
import com.assignment.ijse.serenitymentalhealth.dto.TherapyProgramDto;
import com.assignment.ijse.serenitymentalhealth.entity.TherapyProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapyProgramBOImpl implements TherapyProgramBO {
    private final TherapyProgramDAO therapyProgramDAO = new TherapyProgramDAOImpl();

    @Override
    public boolean saveTherapyProgram(TherapyProgramDto dto) {
        TherapyProgram therapyProgram = new TherapyProgram();
        therapyProgram.setProgramId(dto.getProgramId());
        therapyProgram.setName(dto.getName());
        therapyProgram.setDuration(dto.getDuration());
        therapyProgram.setFee(dto.getFee());
        therapyProgram.setDescription(dto.getDescription());

        return therapyProgramDAO.save(therapyProgram);
    }

    @Override
    public boolean updateTherapyProgram(TherapyProgramDto dto) {
        TherapyProgram therapyProgram = new TherapyProgram();
        therapyProgram.setProgramId(dto.getProgramId());
        therapyProgram.setName(dto.getName());
        therapyProgram.setDuration(dto.getDuration());
        therapyProgram.setFee(dto.getFee());
        therapyProgram.setDescription(dto.getDescription());

        return therapyProgramDAO.update(therapyProgram);
    }

    @Override
    public boolean deleteTherapyProgram(String programId) {
        return therapyProgramDAO.delete(programId);
    }

    @Override
    public ArrayList<TherapyProgramDto> getAllTherapyPrograms() {
        List<TherapyProgram> programs = therapyProgramDAO.getAll();
        ArrayList<TherapyProgramDto> programDtos = new ArrayList<>();

        for (TherapyProgram program : programs) {
            TherapyProgramDto dto = new TherapyProgramDto();
            dto.setProgramId(program.getProgramId());
            dto.setName(program.getName());
            dto.setDuration(program.getDuration());
            dto.setFee(program.getFee());
            dto.setDescription(program.getDescription());
            programDtos.add(dto);
        }
        return programDtos;
    }

    @Override
    public ArrayList<TherapyProgramDto> findTherapyProgramByName(String name) {
        List<TherapyProgram> programs = therapyProgramDAO.findByName(name);
        ArrayList<TherapyProgramDto> dtos = new ArrayList<>();

        for (TherapyProgram program : programs) {
            dtos.add(new TherapyProgramDto(
                    program.getProgramId(),
                    program.getName(),
                    program.getDuration(),
                    program.getFee(),
                    program.getDescription()
            ));
        }

        return dtos;
    }

    @Override
    public String getNextTherapyProgramPK() {
        Optional<String> lastPkOpt = therapyProgramDAO.getLastPK();

        if (lastPkOpt.isPresent()) {
            String lastPk = lastPkOpt.get();
            if (lastPk.startsWith("TP")) {
                String numericPart = lastPk.substring(2);

                try {
                    int currentId = Integer.parseInt(numericPart);
                    int nextId = currentId + 1;

                    return String.format("TP%03d", nextId);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing numeric part of primary key: " + numericPart);
                    return "TP001";
                }
            }
        }

        return "TP001";
    }



}
