package com.assignment.ijse.serenitymentalhealth.bo.custom.impl;

import com.assignment.ijse.serenitymentalhealth.bo.custom.UserBO;
import com.assignment.ijse.serenitymentalhealth.dao.SuperDAO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.UserDAO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.impl.UserDAOImpl;
import com.assignment.ijse.serenitymentalhealth.dto.UserDto;
import com.assignment.ijse.serenitymentalhealth.entity.Patient;
import com.assignment.ijse.serenitymentalhealth.entity.User;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = new UserDAOImpl();

    public String validateUser(String username, String password){
        return userDAO.validateUser(username, password);
    }
    public boolean registerUser(UserDto dto){
        User user = new User();
        user.setUser_id(dto.getUserId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setPassword(dto.getPassword());

        return userDAO.save(user);
    }

}
