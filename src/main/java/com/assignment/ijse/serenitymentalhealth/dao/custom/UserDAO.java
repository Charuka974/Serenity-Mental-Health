package com.assignment.ijse.serenitymentalhealth.dao.custom;

import com.assignment.ijse.serenitymentalhealth.dao.SuperDAO;
import com.assignment.ijse.serenitymentalhealth.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends SuperDAO {

    public boolean save(User entity);
    public boolean update(User entity);
    public boolean delete(String pk);
    public List<User> getAll();
    public Optional<User> findById(String pk);
    public Optional<String> getLastPK();
    public String validateUser(String username, String password);


}
