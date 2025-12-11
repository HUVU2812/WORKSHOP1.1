package com.spa.controller;

import com.spa.dao.UserDAO;
import com.spa.dto.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("UTF-8");
	try {
	    String userID = request.getParameter("userID");
	    String fullName = request.getParameter("fullName");
	    String email = request.getParameter("email");
	    String phoneNumber = request.getParameter("phoneNumber");
	    String password = request.getParameter("password");
	    String roleID = request.getParameter("roleID");
	    Boolean isVIP = false;
	    User newUser = new User(userID, fullName, email, phoneNumber, roleID, password, isVIP);
	    UserDAO userDAO = new UserDAO();

	    // Kiểm tra và đăng ký user
	    String errorMessage = userDAO.registerUser(newUser);
	    if (errorMessage == null) {
		request.setAttribute("SUCCESS", "Registration successful! Click 'Back to Login' to sign in.");
		request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
	    } else {
		request.setAttribute("ERROR", errorMessage);
		request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
	    }
	} catch (Exception e) {
	    log("Error at RegisterController: " + e.toString());
	    request.setAttribute("ERROR", "An error occurred while processing registration.");
	    request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
	}
    }
}
