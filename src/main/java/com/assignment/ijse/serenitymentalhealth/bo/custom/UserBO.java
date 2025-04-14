package com.assignment.ijse.serenitymentalhealth.bo.custom;

import com.assignment.ijse.serenitymentalhealth.dto.UserDto;

public interface UserBO {

    public String validateUser(String username, String password);
    public boolean registerUser(UserDto dto);

}
