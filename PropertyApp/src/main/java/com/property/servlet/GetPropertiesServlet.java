package com.property.servlet;

import com.property.model.Property;
import com.property.service.PropertyService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

@WebServlet("/GetPropertiesServlet")
public class GetPropertiesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Property> list = PropertyService.getAllProperties();
        
        // Manual JSON construction to avoid adding heavy libraries like Jackson for this simple task
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            Property p = list.get(i);
            json.append("{")
                .append("\"id\":\"").append(p.getId()).append("\",")
                .append("\"title\":\"").append(p.getTitle()).append("\",")
                .append("\"location\":\"").append(p.getLocation()).append("\",")
                .append("\"price\":").append(p.getPrice()).append(",")
                .append("\"date\":\"").append(p.getCreateDate()).append("\",")
                .append("\"agent\":{")
                .append("\"agentName\":\"").append(p.getAgent().getAgentName()).append("\",")
                .append("\"phoneNumber\":\"").append(p.getAgent().getPhoneNumber()).append("\",")
                .append("\"agencyName\":\"").append(p.getAgent().getAgencyName()).append("\",")
                .append("\"licenseNumber\":\"").append(p.getAgent().getLicenseNumber()).append("\"")
                .append("}")
                .append("}");
            if (i < list.size() - 1) json.append(",");
        }
        json.append("]");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
    }
}
