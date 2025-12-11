/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.controller;

import com.spa.dao.WaitlistDAO;
import com.spa.dao.ServiceDAO;
import com.spa.dto.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class BookAppointmentController1 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {

            HttpSession session = request.getSession();
            String userID = (String) session.getAttribute("userID");
            String serviceID = request.getParameter("serviceID");
            String appointmentDate = request.getParameter("appointmentDate");
            if (appointmentDate != null && !appointmentDate.isEmpty()) {
                appointmentDate = appointmentDate.replace("T", " ");
            }
            System.out.println(appointmentDate);

            if (userID == null) {
                request.setAttribute("ERROR", "You need to log in to book an appointment.");
                request.getRequestDispatcher("jsp/bookAppointment1.jsp").forward(request, response);
                return;
            }

            if (serviceID == null || serviceID.trim().isEmpty() || appointmentDate == null || appointmentDate.trim().isEmpty()) {
                request.setAttribute("ERROR", "All fields are required.");
                request.getRequestDispatcher("jsp/bookAppointment1.jsp").forward(request, response);
                return;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime appointmentDate1 = LocalDateTime.parse(appointmentDate, formatter);
            LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault()).plus(30, ChronoUnit.MINUTES);
            if (appointmentDate1.isBefore(now)) {
                session.setAttribute("ERROR", "You can only book an appointment in the future, at least 30 minutes from now!");
                response.sendRedirect(request.getContextPath() + "/MainController?action=BookAppointment1");
                return;
            }
            String formattedDate = appointmentDate1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            WaitlistDAO waitlistDAO = new WaitlistDAO();
            boolean success = waitlistDAO.addToWaitlist(userID, serviceID, formattedDate);

            if (success) {
                request.getSession().setAttribute("INFO", "Your appointment request was sent to waitlist (status: waiting).");
                response.sendRedirect("jsp/dashboard.jsp");
                return;
            } else {
                request.setAttribute("ERROR", "Failed to book appointment. Please try again.");
            }

            request.getRequestDispatcher("jsp/bookAppointment1.jsp").forward(request, response);
        } catch (Exception e) {
            log("Error at BookAppointmentController: " + e.toString());
            request.setAttribute("ERROR", "An error occurred while booking the appointment.");
            request.getRequestDispatcher("jsp/bookAppointment1.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ServiceDAO serviceDAO = new ServiceDAO();
            List<Service> services1 = serviceDAO.getAllServices1();
            request.setAttribute("services1", services1);
        } catch (Exception e) {
            log("Error loading services: " + e.toString());
            request.setAttribute("ERROR", "Failed to load services.");
        }
        request.getRequestDispatcher("jsp/bookAppointment1.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Book Appointment Controller";
    }
}
