package com.property.servlet;

import com.property.service.PropertyService;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/DeletePropertyServlet")
public class DeletePropertyServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        if (id != null && !id.trim().isEmpty()) {
            PropertyService.deleteProperty(id.trim());
        }

        response.sendRedirect("viewProperties.html");
    }
}
