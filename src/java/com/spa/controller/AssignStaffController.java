package com.spa.controller;

import com.spa.dao.AppointmentDAO;
import com.spa.dao.UserDAO;
import com.spa.dto.Appointment;
import com.spa.dto.User;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AssignStaffController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AppointmentDAO appointmentDAO = new AppointmentDAO();
            UserDAO userDAO = new UserDAO();

            // Lấy danh sách tất cả các cuộc hẹn
            List<Appointment> appointments = appointmentDAO.getAllAppointments();

            // Lấy danh sách tất cả nhân viên (roleID = 'STF')
            List<User> staffList = userDAO.getAllStaff();
           
            request.setAttribute("appointments", appointments);
            request.setAttribute("staffList", staffList);
        } catch (Exception e) {
            request.setAttribute("ERROR", "Lỗi khi lấy danh sách cuộc hẹn hoặc nhân viên: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("jsp/assignStaff.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller để gán nhân viên cho cuộc hẹn";
    }
}
