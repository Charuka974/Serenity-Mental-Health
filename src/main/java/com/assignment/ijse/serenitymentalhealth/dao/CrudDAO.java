package com.assignment.ijse.serenitymentalhealth.dao;

import com.assignment.ijse.serenitymentalhealth.entity.SuperEntity;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity, ID> extends SuperDAO{

    public boolean save(T entity);
    public boolean update(T entity);
    public boolean delete(ID pk);
    public List<T> getAll();
    public Optional<T> findById(ID pk);

    public Optional<String> getLastPK();

}
