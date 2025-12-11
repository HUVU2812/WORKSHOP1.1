package com.spa.controller;

import com.spa.dao.ConsultationDAO;
import com.spa.dao.ConsumableDAO;
import com.spa.dao.UsageLogDAO;
import com.spa.dto.Appointment;
import com.spa.dto.ConsumableDTO;
import com.spa.dto.ConsumableUsageLogDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "InventoryUsageController", urlPatterns = {"/InventoryUsageController"})
public class InventoryUsageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ConsumableDAO cdao = new ConsumableDAO();
        UsageLogDAO udao = new UsageLogDAO();

        try {
            // Lấy toàn bộ tồn kho
            List<ConsumableDTO> consumables = cdao.getAllConsumables();
            HttpSession session = request.getSession();
            session.setAttribute("ListConsumable", consumables);

            String staffID = (String) session.getAttribute("userID");
            ConsultationDAO dao = new ConsultationDAO();
            List<Appointment> consultations = dao.getAllConsultations(staffID);
            List<Appointment> consultationsForAd = dao.getAllAppointmentsOfStaffs();
            session.setAttribute("consultationsForAd", consultationsForAd);
            session.setAttribute("consultations", consultations);

            List<ConsumableUsageLogDTO> logs = udao.getAllUsageLogs();
            request.setAttribute("USAGE_LOGS", logs);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("jsp/consumable.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }
}
