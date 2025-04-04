package com.assignment.ijse.serenitymentalhealth.dao.custom;

import com.assignment.ijse.serenitymentalhealth.entity.User;


import java.util.List;
import java.util.Optional;

public interface UserDAO{

    public boolean save(User entity);
    public boolean update(User entity);
    public boolean delete(String pk);
    public List<User> getAll();
    public Optional<User> findByName(String pk);
    public Optional<String> getLastPK();
    public String validateUser(String username, String password);


}
