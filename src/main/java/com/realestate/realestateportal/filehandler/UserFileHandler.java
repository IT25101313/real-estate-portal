package com.realestate.realestateportal.filehandler;

import com.realestate.realestateportal.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFileHandler implements FileHandler<User> {

    private static final String FILE_PATH = "data/users.txt";

    // CREATE — appends a new user as one line to users.txt
    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_PATH, true))) {
            writer.write(user.toFileString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    // READ — returns all users from users.txt
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return users;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    users.add(User.fromFileString(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users: " + e.getMessage());
        }
        return users;
    }

    // READ — finds one user by id
    @Override
    public User findById(String id) {
        for (User user : findAll()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    // Find user by email (used for login)
    public User findByEmail(String email) {
        for (User user : findAll()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    // UPDATE — rewrites the whole file with updated user
    @Override
    public void update(User updatedUser) {
        List<User> users = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (User user : users) {
                if (user.getId().equals(updatedUser.getId())) {
                    writer.write(updatedUser.toFileString());
                } else {
                    writer.write(user.toFileString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    // DELETE — rewrites the file skipping the deleted user
    @Override
    public void delete(String id) {
        List<User> users = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (User user : users) {
                if (!user.getId().equals(id)) {
                    writer.write(user.toFileString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }
}