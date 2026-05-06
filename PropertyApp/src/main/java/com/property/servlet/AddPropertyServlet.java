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

@WebServlet("/AddPropertyServlet")
public class AddPropertyServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Generate unique ID
        String id = "P" + System.currentTimeMillis();
        
        String title = request.getParameter("title");
        String location = request.getParameter("location");
        double price = Double.parseDouble(request.getParameter("price"));
        if (price <= 0) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Price must be greater than 0.");
            return;
        }
        
        String agentName = request.getParameter("agentName");
        String phoneNumber = request.getParameter("phoneNumber");
        if (phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Phone number must be exactly 10 digits.");
            return;
        }
        String agencyName = request.getParameter("agencyName");
        String licenseNumber = request.getParameter("licenseNumber");

        Agent agent = new Agent(agentName, phoneNumber, agencyName, licenseNumber);
        String createDate = java.time.LocalDate.now().toString();
        Property property = new Property(id, title, location, price, agent, createDate);
        PropertyService.addProperty(property);

        response.sendRedirect("viewProperties.html");
    }
}
