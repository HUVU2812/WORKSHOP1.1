package com.spa.controller;

import com.spa.dao.ConsumableDAO;
import com.spa.dao.UsageLogDAO;
import com.spa.dto.ConsumableDTO;
import com.spa.dto.ConsumableUsageLogDTO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddUsageLogController", urlPatterns = {"/AddUsageLogController"})
public class AddUsageLogController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String consumableID = request.getParameter("consumableID");
            String appointmentID = request.getParameter("appointmentID");
            String quantityUsedRaw = request.getParameter("quantityUsed");
            String usedAt = request.getParameter("usedAt");
            int quantityUsed = 0;

            // VALIDATE INPUTS
            // 1. Consumable ID: cs*** (* là số)
            if (consumableID == null || !consumableID.matches("^(?i)^cs\\d+$")) {
                request.setAttribute("ERROR", "Consumable ID phải dạng 'cs' theo sau bởi số!");
                request.getRequestDispatcher("/jsp/addUsageLog.jsp").forward(request, response);
                return;
            }

            // 2. Appointment ID: Không rỗng (giả sử validate thêm nếu cần)
            if (appointmentID == null || appointmentID.trim().isEmpty()) {
                request.setAttribute("ERROR", "Appointment ID không được để trống!");
                request.getRequestDispatcher("/jsp/addUsageLog.jsp").forward(request, response);
                return;
            }

            // 3. Quantity Used: Số nguyên > 0
            try {
                quantityUsed = Integer.parseInt(quantityUsedRaw);
                if (quantityUsed <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                request.setAttribute("ERROR", "Quantity Used phải là số nguyên lớn hơn 0!");
                request.getRequestDispatcher("/jsp/addUsageLog.jsp").forward(request, response);
                return;
            }

            // 4. Used At: Không rỗng (date validate bởi input type=date)
            if (usedAt == null || usedAt.trim().isEmpty()) {
                request.setAttribute("ERROR", "Used At không được để trống!");
                request.getRequestDispatcher("/jsp/addUsageLog.jsp").forward(request, response);
                return;
            }

            // INSERT LOG
            UsageLogDAO dao = new UsageLogDAO();

            if (dao.updateStock(consumableID, -quantityUsed)) {
                boolean ok = dao.insertConsumableUsageLog(consumableID, appointmentID, quantityUsed, usedAt);
                if (ok) {
                    request.setAttribute("SUCCESS", "Usage Log added successfully! Stock updated.");
                } else {
                    request.setAttribute("ERROR", "Failed to add usage log!");
                }
            } else {
                request.setAttribute("ERROR", "Failed to add usage log! (Check if IDs exist or stock insufficient)");
            }

            request.getRequestDispatcher("/jsp/addUsageLog.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddUsageLogController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("ERROR", "Error: " + ex.getMessage());
            request.getRequestDispatcher("/jsp/addUsageLog.jsp").forward(request, response);
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
        return "Add Usage Log Controller";
    }
}
