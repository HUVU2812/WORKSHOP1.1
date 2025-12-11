package com.spa.controller;
import com.spa.dao.AppointmentDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CancelAppointmentController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String appointmentID = request.getParameter("appointmentID");

        try {
            if (appointmentID == null || appointmentID.trim().isEmpty()) {
                request.setAttribute("ERROR", "Invalid appointment ID.");
                request.getRequestDispatcher("jsp/appointmentList.jsp").forward(request, response);
                return;
            }

            AppointmentDAO dao = new AppointmentDAO();
            boolean success = dao.cancelAppointment(appointmentID);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/MainController?action=ViewAppointments"); // Load lại danh sách
            } else {
                request.setAttribute("ERROR", "Failed to cancel appointment.");
                request.getRequestDispatcher("jsp/appointmentList.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "An error occurred while canceling.");
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }
    }
}
