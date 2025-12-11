/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.controller;

import com.spa.dao.ReviewDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class CancelReview extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reviewID = request.getParameter("reviewID");

        try {
            if (reviewID == null || reviewID.trim().isEmpty()) {
                request.setAttribute("ERROR", "Invalid appointment ID.");
                request.getRequestDispatcher("jsp/viewFeedback.jsp").forward(request, response);
                return;
            }

            ReviewDAO dao = new ReviewDAO();
            boolean success = dao.cancelReview(reviewID);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/MainController?action=Feedback"); // Load lại danh sách
            } else {
                request.setAttribute("ERROR", "Failed to cancel appointment.");
                request.getRequestDispatcher("jsp/viewFeedback.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "An error occurred while canceling.");
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }
    }
}
