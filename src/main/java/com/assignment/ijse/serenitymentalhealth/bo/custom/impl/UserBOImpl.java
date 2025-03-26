package com.assignment.ijse.serenitymentalhealth.bo.custom.impl;

import com.assignment.ijse.serenitymentalhealth.bo.custom.UserBO;
import com.assignment.ijse.serenitymentalhealth.dao.SuperDAO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.UserDAO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.impl.UserDAOImpl;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = new UserDAOImpl();

    public String validateUser(String username, String password){
        return userDAO.validateUser(username, password);
    }

}
