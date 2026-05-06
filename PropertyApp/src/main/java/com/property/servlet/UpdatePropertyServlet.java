package com.property.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.property.model.Agent;
import com.property.model.Property;
import com.property.service.PropertyService;

@WebServlet("/UpdatePropertyServlet")
public class UpdatePropertyServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        
        // Fetch existing property
        Property existing = PropertyService.getPropertyById(id);
        if (existing == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Property not found");
            return;
        }
        
        // Update only provided fields
        String title = request.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            title = existing.getTitle();
        }
        
        String location = request.getParameter("location");
        if (location == null || location.trim().isEmpty()) {
            location = existing.getLocation();
        }
        
        double price = existing.getPrice();
        String priceParam = request.getParameter("price");
        if (priceParam != null && !priceParam.trim().isEmpty()) {
            try {
                price = Double.parseDouble(priceParam);
                if (price <= 0) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Price must be greater than 0.");
                    return;
                }
            } catch (NumberFormatException e) {
                // Keep existing price if invalid
            }
        }
        
        Agent agent = existing.getAgent();
        String agentName = request.getParameter("agentName");
        String phoneNumber = request.getParameter("phoneNumber");
        if (phoneNumber != null && !phoneNumber.trim().isEmpty() && !phoneNumber.matches("\\d{10}")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Phone number must be exactly 10 digits.");
            return;
        }
        String agencyName = request.getParameter("agencyName");
        String licenseNumber = request.getParameter("licenseNumber");
        
        if (agentName != null && !agentName.trim().isEmpty() ||
            phoneNumber != null && !phoneNumber.trim().isEmpty() ||
            agencyName != null && !agencyName.trim().isEmpty() ||
            licenseNumber != null && !licenseNumber.trim().isEmpty()) {
            // Update agent details if any provided
            if (agentName == null || agentName.trim().isEmpty()) agentName = agent.getAgentName();
            if (phoneNumber == null || phoneNumber.trim().isEmpty()) phoneNumber = agent.getPhoneNumber();
            if (agencyName == null || agencyName.trim().isEmpty()) agencyName = agent.getAgencyName();
            if (licenseNumber == null || licenseNumber.trim().isEmpty()) licenseNumber = agent.getLicenseNumber();
            agent = new Agent(agentName, phoneNumber, agencyName, licenseNumber);
        }
        
        String createDate = existing.getCreateDate();
        
        Property property = new Property(id, title, location, price, agent, createDate);
        PropertyService.updateProperty(property);

        response.sendRedirect("viewProperties.html");
    }
}
