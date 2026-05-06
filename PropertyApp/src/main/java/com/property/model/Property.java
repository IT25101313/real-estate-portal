package com.property.model;

public class Property {
    private String id;
    private String title;
    private String location;
    private double price;
    private Agent agent;
    private String createDate;

    public Property(String id, String title, String location, double price, Agent agent, String createDate) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.price = price;
        this.agent = agent;
        this.createDate = createDate;
    }

    public String toFileString() {
        return id + "," + title + "," + location + "," + price + "," + 
               agent.getAgentName() + "," + agent.getPhoneNumber() + "," + 
               agent.getAgencyName() + "," + agent.getLicenseNumber() + "," +
               (createDate != null ? createDate : "No Date");
    }

    public static Property fromFileString(String line) {
        String[] data = line.split(",", -1);
        if (data.length == 5) {
            // Backwards compatibility with old text file records
            Agent agent = new Agent("Not specified", "Not specified", "Not specified", data[4]);
            return new Property(data[0], data[1], data[2], Double.parseDouble(data[3]), agent, "No Date");
        } else {
            Agent agent = new Agent(data[4], data[5], data[6], data[7]);
            String date = data.length > 8 ? data[8] : "No Date";
            return new Property(data[0], data[1], data[2], Double.parseDouble(data[3]), agent, date);
        }
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public double getPrice() { return price; }
    public Agent getAgent() { return agent; }
    public String getCreateDate() { return createDate; }
}
