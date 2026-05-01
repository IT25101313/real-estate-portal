package com.realestate.realestateportal.filehandler;

import com.realestate.realestateportal.model.BaseEntity;
import java.util.List;

public interface FileHandler<T extends BaseEntity> {

    // Create — saves a new record to the .txt file
    void save(T entity);

    // Read — finds one record by its id
    T findById(String id);

    // Read — returns all records from the .txt file
    List<T> findAll();

    // Update — overwrites an existing record with new data
    void update(T entity);

    // Delete — removes a record by its id
    void delete(String id);
}