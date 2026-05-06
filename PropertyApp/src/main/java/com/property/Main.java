package com.property;

import com.property.servlet.AddPropertyServlet;
import com.property.servlet.UpdatePropertyServlet;
import com.property.servlet.DeletePropertyServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        WebAppContext context = new WebAppContext();
        context.setContextPath("/PropertyApp");
        
        java.io.File webappDir = new java.io.File("PropertyApp/src/main/webapp");
        if (!webappDir.exists()) {
            webappDir = new java.io.File("src/main/webapp");
        }
        context.setResourceBase(webappDir.getAbsolutePath());
        context.setWelcomeFiles(new String[]{"index.html"});
        context.setParentLoaderPriority(true);

        context.addServlet(AddPropertyServlet.class,    "/AddPropertyServlet");
        context.addServlet(UpdatePropertyServlet.class, "/UpdatePropertyServlet");
        context.addServlet(DeletePropertyServlet.class, "/DeletePropertyServlet");
        context.addServlet(com.property.servlet.GetPropertiesServlet.class, "/GetPropertiesServlet");

        server.setHandler(context);
        server.start();

        System.out.println("===========================================");
        System.out.println("  Server started!");
        System.out.println("  Open: http://localhost:8080/PropertyApp/");
        System.out.println("  Press Ctrl+C to stop.");
        System.out.println("===========================================");

        server.join();
    }
}
