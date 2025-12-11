package com.spa.controller;

import com.spa.dao.WaitlistDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ApproveWaitlistController", urlPatterns = {"/ApproveWaitlistController"})
public class ApproveWaitlistController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String roleID = (session != null) ? (String) session.getAttribute("roleID") : null;
        if (roleID == null || !( "ADM".equals(roleID) || "STF".equals(roleID) )) {
            request.getSession().setAttribute("ERROR", "Access denied.");
            response.sendRedirect("jsp/dashboard.jsp");
            return;
        }

        String waitID = request.getParameter("waitID");
        try {
            WaitlistDAO dao = new WaitlistDAO();
            boolean ok = dao.approve(waitID);
            if (!ok) {
                request.getSession().setAttribute("ERROR", "Approve failed or item not found.");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("ERROR", "Approve error: " + e.getMessage());
        }
        response.sendRedirect("MainController?action=ViewWaitlist");
    }
}
