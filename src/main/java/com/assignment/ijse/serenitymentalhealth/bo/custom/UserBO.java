package com.assignment.ijse.serenitymentalhealth.bo.custom;

import com.assignment.ijse.serenitymentalhealth.bo.SuperBO;
import com.assignment.ijse.serenitymentalhealth.dto.PatientDto;
import com.assignment.ijse.serenitymentalhealth.dto.UserDto;

import java.util.ArrayList;

public interface UserBO extends SuperBO {

    public String validateUser(String username, String password);
    public boolean registerUser(UserDto dto);
    public boolean updateUser(UserDto dto);
    public boolean deleteUser(String userId);
    public ArrayList<UserDto> searchUser(String userId);
    public String generateNextUserId();
    public ArrayList<UserDto> getAllUsers();
    public UserDto findUserByUserId(String userId);
    public boolean updateUsernameAndPassword(String userId, String newUsername, String newPassword);


}
