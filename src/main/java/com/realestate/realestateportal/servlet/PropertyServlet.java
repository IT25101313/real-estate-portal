package com.realestate.realestateportal.servlet;

import com.realestate.realestateportal.model.Property;
import com.realestate.realestateportal.model.User;
import com.realestate.realestateportal.service.PropertyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/seller")
public class PropertyServlet extends HttpServlet {

    private PropertyService propertyService = new PropertyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        String action = request.getParameter("action");

        if (action == null || "list".equals(action)) {
            List<Property> myProperties = propertyService.getPropertiesBySeller(loggedInUser.getId());
            request.setAttribute("properties", myProperties);
            request.getRequestDispatcher("seller_dashboard.jsp").forward(request, response);
        } else if ("add".equals(action)) {
            request.getRequestDispatcher("add_property.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            String id = request.getParameter("id");
            Property property = propertyService.getPropertyById(id);
            request.setAttribute("property", property);
            request.getRequestDispatcher("edit_property.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            String type = request.getParameter("type");
            String location = request.getParameter("location");
            
            propertyService.addProperty(title, description, price, type, location, loggedInUser.getId());
            response.sendRedirect("seller?action=list&success=added");
            
        } else if ("update".equals(action)) {
            String id = request.getParameter("id");
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            String type = request.getParameter("type");
            String location = request.getParameter("location");
            
            propertyService.updateProperty(id, title, description, price, type, location, loggedInUser.getId());
            response.sendRedirect("seller?action=list&success=updated");
            
        } else if ("delete".equals(action)) {
            String id = request.getParameter("id");
            propertyService.deleteProperty(id);
            response.sendRedirect("seller?action=list&success=deleted");
        }
    }
}
