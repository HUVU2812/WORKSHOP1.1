package com.spa.controller;

import com.spa.dao.ServiceDAO;
import com.spa.dto.Service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateServiceController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServiceDAO dao = new ServiceDAO();
        String url = "jsp/editService.jsp";

        String serviceID = request.getParameter("serviceID");
        String serviceName = request.getParameter("serviceName");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
	String statusStr = request.getParameter("status");

        if (serviceID == null || serviceID.trim().isEmpty()
                || serviceName == null || serviceName.trim().isEmpty()
                || description == null || description.trim().isEmpty()
                || priceStr == null || priceStr.trim().isEmpty()
		|| statusStr == null || statusStr.trim().isEmpty()) {
            request.setAttribute("ERROR", "All fields are required.");
        } else {
            try {
                double price = Double.parseDouble(priceStr);
		boolean status = Boolean.parseBoolean(statusStr);
                if (price <= 0) {
                    request.setAttribute("ERROR", "Price must be greater than zero.");
                } else {

                    Service service = new Service(serviceID, serviceName, description, price, status);

                    if (dao.updateService(service)) {
                        url ="MainController?action=ViewServices";
                    } else {
                        request.setAttribute("ERROR", "Failed to update service.");
                    }
                }
            } catch (Exception e) {
                log("Error at UpdateServiceController: " + e.toString());
                request.setAttribute("ERROR", "An unexpected error occurred.");
            }
        }
        try {
            Service service = dao.getServiceById(serviceID);
            if (service != null) {
                request.setAttribute("service", service);
            }
        } catch (Exception e) {
            log("Error fetching service: " + e.toString());
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("jsp/editService.jsp");  // Redirect to form if accessed via GET
    }

    @Override
    public String getServletInfo() {
        return "Update Service Controller";
    }
}
