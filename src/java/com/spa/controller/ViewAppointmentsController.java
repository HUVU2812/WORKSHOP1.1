package com.spa.controller;

import com.spa.dao.AppointmentDAO;
import com.spa.dto.Appointment;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewAppointmentsController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userID");

        if (userID == null) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }

        try {
            AppointmentDAO dao = new AppointmentDAO();
            List<Appointment> appointments = dao.getAppointmentsByUser(userID);
            List<Appointment> listAppoinment = dao.getAllAppointments();
            request.setAttribute("listAppointment", listAppoinment);
            request.setAttribute("appointments", appointments);
            request.getRequestDispatcher("/jsp/appointmentList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Could not load appointments.");
            
        }
    }
}
