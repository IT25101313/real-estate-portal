package com.realestate.realestateportal.filehandler;

import com.realestate.realestateportal.model.Property;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyFileHandler implements FileHandler<Property> {

    private static final String FILE_PATH = "data/properties.txt";

    @Override
    public void save(Property property) {
        ensureDirectoryExists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(property.toFileString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving property: " + e.getMessage());
        }
    }

    @Override
    public List<Property> findAll() {
        List<Property> properties = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return properties;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    properties.add(Property.fromFileString(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading properties: " + e.getMessage());
        }
        return properties;
    }

    @Override
    public Property findById(String id) {
        for (Property p : findAll()) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public List<Property> findBySellerId(String sellerId) {
        List<Property> sellerProperties = new ArrayList<>();
        for (Property p : findAll()) {
            if (p.getSellerId().equals(sellerId)) {
                sellerProperties.add(p);
            }
        }
        return sellerProperties;
    }

    @Override
    public void update(Property updatedProperty) {
        List<Property> properties = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Property p : properties) {
                if (p.getId().equals(updatedProperty.getId())) {
                    writer.write(updatedProperty.toFileString());
                } else {
                    writer.write(p.toFileString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating property: " + e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        List<Property> properties = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Property p : properties) {
                if (!p.getId().equals(id)) {
                    writer.write(p.toFileString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting property: " + e.getMessage());
        }
    }

    private void ensureDirectoryExists() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
