package com.spa.controller;

import com.spa.dao.ConsultationDAO;
import com.spa.dto.Appointment;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewConsultationController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
	    String staffID = (String) request.getSession().getAttribute("userID"); // Lấy userID của nhân viên đăng nhập
        if (staffID == null) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }
            ConsultationDAO dao = new ConsultationDAO();
            List<Appointment> consultations = dao.getAllConsultations(staffID);
            request.setAttribute("consultations", consultations);
            request.getRequestDispatcher("/jsp/consultation.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Could not load consultations.");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}
