package com.property.service;

import com.property.model.Property;
import java.io.*;
import java.util.*;

public class PropertyService {

    private static final String FILE_NAME;
    static {
        if (new java.io.File("PropertyApp/properties.txt").exists() || new java.io.File("PropertyApp").exists()) {
            FILE_NAME = "PropertyApp/properties.txt";
        } else {
            FILE_NAME = "properties.txt";
        }
    }

    public static void addProperty(Property property) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
        writer.write(property.toFileString());
        writer.newLine();
        writer.close();
    }

    public static List<Property> getAllProperties() throws IOException {
        List<Property> list = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return list;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            list.add(Property.fromFileString(line));
        }
        reader.close();
        return list;
    }

    public static void deleteProperty(String id) throws IOException {
        List<Property> list = getAllProperties();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));

        for (Property p : list) {
            if (!p.getId().equals(id)) {
                writer.write(p.toFileString());
                writer.newLine();
            }
        }
        writer.close();
    }

    public static Property getPropertyById(String id) throws IOException {
        List<Property> list = getAllProperties();
        for (Property p : list) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public static void updateProperty(Property updatedProperty) throws IOException {
        List<Property> list = getAllProperties();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));

        for (Property p : list) {
            if (p.getId().equals(updatedProperty.getId())) {
                writer.write(updatedProperty.toFileString());
            } else {
                writer.write(p.toFileString());
            }
            writer.newLine();
        }
        writer.close();
    }
}
