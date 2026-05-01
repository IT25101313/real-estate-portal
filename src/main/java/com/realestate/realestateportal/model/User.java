package com.realestate.realestateportal.model;

public class User extends BaseEntity {

    private String name;
    private String email;
    private String password;
    private String role; // "admin" or "user"

    public User(String id, String name, String email, String password, String role) {
        super(id); // calls BaseEntity constructor
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }

    // Converts user object to one pipe-separated line for users.txt
    // Format: id|name|email|password|role
    @Override
    public String toFileString() {
        return getId() + "|" + name + "|" + email + "|" + password + "|" + role;
    }

    // Rebuilds a User object from a line read from users.txt
    public static User fromFileString(String line) {
        String[] parts = line.split("\\|");
        return new User(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }
}