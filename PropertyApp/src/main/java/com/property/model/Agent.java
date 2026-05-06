package com.property.model;

public class Agent {
    private String agentName;
    private String phoneNumber;
    private String agencyName;
    private String licenseNumber;

    public Agent(String agentName, String phoneNumber, String agencyName, String licenseNumber) {
        this.agentName = agentName;
        this.phoneNumber = phoneNumber;
        this.agencyName = agencyName;
        this.licenseNumber = licenseNumber;
    }

    public String getAgentName() { return agentName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAgencyName() { return agencyName; }
    public String getLicenseNumber() { return licenseNumber; }
}
