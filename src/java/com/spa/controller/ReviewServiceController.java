package com.spa.controller;

import com.spa.dao.ReviewDAO;
import com.spa.dao.ServiceDAO;
import com.spa.dto.Review;
import com.spa.dto.Service;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReviewServiceController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            HttpSession session = request.getSession();
            String userID = (String) session.getAttribute("userID");
            String serviceID = request.getParameter("serviceID");
            int rating = Integer.parseInt(request.getParameter("rating"));
            String comments = request.getParameter("comments");
            Boolean isVIP = (Boolean) session.getAttribute("isVIP");
            if (userID == null) {
                request.setAttribute("ERROR", "You need to log in to submit a review.");
                request.getRequestDispatcher("jsp/reviewService.jsp").forward(request, response);
                return;
            }
            
            if (serviceID == null || serviceID.trim().isEmpty() || rating < 1 || rating > 5) {
                request.setAttribute("ERROR", "Invalid input. Please provide all required fields.");
                request.getRequestDispatcher("jsp/reviewService.jsp").forward(request, response);
                return;
            }
            
            String reviewID = "rvs" + System.currentTimeMillis();
            Review review = new Review(reviewID, userID, serviceID, rating, comments,"","", true, isVIP);
            ReviewDAO reviewDAO = new ReviewDAO();
            
            boolean success = reviewDAO.addReview(review);
            if (success) {
                response.sendRedirect("jsp/dashboard.jsp");
            } else {
                request.setAttribute("ERROR", "Failed to submit review. Please try again.");
                request.getRequestDispatcher("jsp/reviewService.jsp").forward(request, response);
            }
        } catch (Exception e) {
            log("Error at ReviewServiceController: " + e.toString());
            request.setAttribute("ERROR", "An error occurred while submitting your review.");
            request.getRequestDispatcher("jsp/reviewService.jsp").forward(request, response);
        }
    }
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        ServiceDAO serviceDAO = new ServiceDAO();
        List<Service> services = serviceDAO.getAllServices();
        request.setAttribute("services", services);
    } catch (Exception e) {
        log("Error loading services: " + e.toString());
        request.setAttribute("ERROR", "Failed to load services.");
    }
    request.getRequestDispatcher("jsp/reviewService.jsp").forward(request, response);
}

}
