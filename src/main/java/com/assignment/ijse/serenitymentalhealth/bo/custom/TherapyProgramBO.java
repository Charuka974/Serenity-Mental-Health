package com.assignment.ijse.serenitymentalhealth.bo.custom;

import com.assignment.ijse.serenitymentalhealth.bo.SuperBO;
import com.assignment.ijse.serenitymentalhealth.dto.TherapyProgramDto;

import java.util.ArrayList;

public interface TherapyProgramBO extends SuperBO {

    public boolean saveTherapyProgram(TherapyProgramDto dto);
    public boolean updateTherapyProgram(TherapyProgramDto dto);
    public boolean deleteTherapyProgram(String programId);
    public ArrayList<TherapyProgramDto> getAllTherapyPrograms();
    public ArrayList<TherapyProgramDto> findTherapyProgramByName(String name);
    public String getNextTherapyProgramPK();
    public TherapyProgramDto findTherapyProgramByID(String id);

}
