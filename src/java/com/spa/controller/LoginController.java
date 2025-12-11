/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.controller;

import com.spa.dao.ConsumableDAO;
import com.spa.dao.UserDAO;
import com.spa.dto.ConsumableDTO;
import com.spa.dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.authenticateUser(email, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userID", user.getUserID());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("fullName", user.getFullName());
                session.setAttribute("roleID", user.getRoleID());
                session.setAttribute("isVIP", user.getIsVIP());
                response.sendRedirect(request.getContextPath() + "/jsp/dashboard.jsp");
            } else {
                request.setAttribute("errorMessage", "Invalid email or password. Please try again.");
                request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred. Please try again.");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }
    }

}
