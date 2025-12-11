package com.spa.controller;

import com.spa.dao.ServiceDAO;
import com.spa.dto.Service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateServiceController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/jsp/createService.jsp";  // Default return page
        String serviceName = request.getParameter("serviceName");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
	String statusStr = request.getParameter("status");


        if (serviceName == null || serviceName.trim().isEmpty() ||
            description == null || description.trim().isEmpty() ||
            priceStr == null || priceStr.trim().isEmpty()) {
            request.setAttribute("ERROR", "All fields are required.");
        } else {
            try {
                double price = Double.parseDouble(priceStr);
		boolean status = Boolean.parseBoolean(statusStr);
                if (price <= 0) {
                    request.setAttribute("ERROR", "Price must be greater than zero.");
                } else {
                    ServiceDAO dao = new ServiceDAO();
                    String serviceID = dao.generateNewserviceID();
                    Service service = new Service(serviceID, serviceName, description, price, status);
                    
                    if (dao.createService(service)) {
                        url ="/jsp/dashboard.jsp";  
                    } else {
                        request.setAttribute("ERROR", "Failed to create service.");
                    }
                }
            } catch (Exception e) {
                log("Error at CreateServiceController: " + e.toString());
                request.setAttribute("ERROR", "An unexpected error occurred.");
            }
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("jsp/createService.jsp");  // Redirect to form if accessed via GET
    }

    @Override
    public String getServletInfo() {
        return "Create Service Controller";
    }
}
