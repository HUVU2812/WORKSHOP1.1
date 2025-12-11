package com.spa.controller;

import com.spa.dao.WaitlistDAO;
import com.spa.dao.ServiceDAO;
import com.spa.dto.WaitlistEntry;
import com.spa.dto.Service;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ViewWaitlistController", urlPatterns = {"/ViewWaitlistController"})
public class ViewWaitlistController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String roleID = (session != null) ? (String) session.getAttribute("roleID") : null;
        if (roleID == null || !( "ADM".equals(roleID) || "STF".equals(roleID) )) {
            request.setAttribute("ERROR", "Access denied.");
            request.getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response);
            return;
        }

        String dateStr = request.getParameter("date");        // yyyy-MM-dd
        String serviceID = request.getParameter("serviceID"); // serviceID hoặc "all"

        try {
            WaitlistDAO dao = new WaitlistDAO();
            List<WaitlistEntry> list = dao.find(dateStr, serviceID);
            request.setAttribute("waitlist", list);
            request.setAttribute("count", (list != null ? list.size() : 0));

            ServiceDAO sdao = new ServiceDAO();
            List<Service> services = sdao.getAllServices();
            request.setAttribute("services", services);
            request.setAttribute("selectedDate", dateStr);
            request.setAttribute("selectedServiceID", serviceID);
        } catch (Exception e) {
            request.setAttribute("ERROR", "Failed to load waitlist: " + e.getMessage());
        }

        request.getRequestDispatcher("/jsp/waitlist.jsp").forward(request, response);
    }
}
