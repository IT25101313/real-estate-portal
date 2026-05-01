package com.realestate.realestateportal.model;

public abstract class BaseEntity {

    private String id;

    public BaseEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Every child class must implement this —
    // converts the object to one line for saving in .txt file
    public abstract String toFileString();
}