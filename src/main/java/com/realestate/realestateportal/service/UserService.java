package com.realestate.realestateportal.service;

import com.realestate.realestateportal.filehandler.UserFileHandler;
import com.realestate.realestateportal.model.User;

import java.util.List;
import java.util.UUID;

public class UserService {

    private UserFileHandler userFileHandler = new UserFileHandler();

    // REGISTER — creates a new user after checking email doesn't exist
    public boolean register(String name, String email, String password, String role) {
        if (userFileHandler.findByEmail(email) != null) {
            return false; // email already exists
        }
        String id = "U" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        User newUser = new User(id, name, email, password, role);
        userFileHandler.save(newUser);
        return true;
    }

    // LOGIN — checks email and password match
    public User login(String email, String password) {
        User user = userFileHandler.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // GET ALL USERS
    public List<User> getAllUsers() {
        return userFileHandler.findAll();
    }

    // GET USER BY ID
    public User getUserById(String id) {
        return userFileHandler.findById(id);
    }

    // UPDATE USER
    public void updateUser(String id, String name, String email, String password, String role) {
        User user = new User(id, name, email, password, role);
        userFileHandler.update(user);
    }

    // DELETE USER
    public void deleteUser(String id) {
        userFileHandler.delete(id);
    }
}