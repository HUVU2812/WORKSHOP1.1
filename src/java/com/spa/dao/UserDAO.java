/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.dao;

import com.spa.dto.User;
import com.spa.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class UserDAO {

    private static final String PHONE_PATTERN = "^(03|07|09)\\d{8}$";
    private static final String EMAIL_PATTERN = "^[\\w.-]+@[\\w.-]+\\.com$";

    public User authenticateUser(String email, String password) throws Exception {
	String sql = "SELECT userID, fullName, email, phoneNumber, roleID, isVIP FROM tblUsers WHERE email = ? AND password = ?";
	try (Connection conn = DatabaseConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql)) {
	    stmt.setString(1, email);
	    stmt.setString(2, password);
	    try (ResultSet rs = stmt.executeQuery()) {
		if (rs.next()) {
		    return new User(
			    rs.getString("userID"),
			    rs.getString("fullName"),
			    rs.getString("email"),
			    rs.getString("phoneNumber"),
			    rs.getString("roleID"),
			    null, // Không trả về mật khẩu vì lý do bảo mật
			    rs.getBoolean("isVIP")
		    );
		}
	    }
	}
	return null;
    }

    public String registerUser(User user) {
	try (Connection con = DatabaseConnection.getConnection()) {
	    // Kiểm tra dữ liệu đầu vào
	    if (!Pattern.matches(PHONE_PATTERN, user.getPhoneNumber())) {
		return "Invalid phone number. Must start with 03, 07, 09 and have 10 digits.";
	    }

	    if (!Pattern.matches(EMAIL_PATTERN, user.getEmail())) {
		return "Invalid email format. Must contain '@' and end with '.com'.";
	    }

	    // Kiểm tra userID đã tồn tại chưa
	    String checkUserSql = "SELECT userID FROM tblUsers WHERE userID = ?";
	    try (PreparedStatement checkStmt = con.prepareStatement(checkUserSql)) {
		checkStmt.setString(1, user.getUserID());
		ResultSet rs = checkStmt.executeQuery();
		if (rs.next()) {
		    return "UserID already exists. Please choose another.";
		}
	    }

	    // Chèn dữ liệu vào bảng tblUsers
	    String sql = "INSERT INTO tblUsers(userID, fullName, email, phoneNumber, roleID, password, isVIP) VALUES(?, ?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
		ps.setString(1, user.getUserID());
		ps.setString(2, user.getFullName());
		ps.setString(3, user.getEmail());
		ps.setString(4, user.getPhoneNumber());
		ps.setString(5, user.getRoleID());
		ps.setString(6, user.getPassword());
		if ("USR".equals(user.getRoleID())) {
		    ps.setBoolean(7, false);
		} else if ("STF".equals(user.getRoleID())) {
		    ps.setNull(7, java.sql.Types.BOOLEAN);
		}

		int rowsInserted = ps.executeUpdate();
		if (rowsInserted > 0) {
		    return null; // Không có lỗi, đăng ký thành công
		}
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "Registration failed. Please try again.";
    }

    public List<User> getAllStaff() throws Exception {
	List<User> staffList = new ArrayList<>();
	String sql = "SELECT userID, fullName FROM tblUsers WHERE roleID = 'STF'";
	try (Connection con = DatabaseConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery()) {

	    while (rs.next()) {
		staffList.add(new User(rs.getString("userID"), rs.getString("fullName"), null, null, "STF", null, null));
	    }
	}
	return staffList;
    }

    public List<User> getAllUser() throws Exception {
	List<User> userList = new ArrayList<>();
	String sql = "SELECT userID, fullName, email, phoneNumber, isVIP FROM tblUsers WHERE roleID = 'USR'";
	try (Connection con = DatabaseConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery()) {

	    while (rs.next()) {
		boolean isVIP = rs.getBoolean("isVIP"); // Đọc dữ liệu BIT
		userList.add(new User(rs.getString("userID"), rs.getString("fullName"),
			rs.getString("email"), rs.getString("phoneNumber"), "USR", null, isVIP));
	    }
	}
	return userList;
    }

    public void updateUserVIP(String userID, boolean isVIP) throws Exception {
	String sql = "UPDATE tblUsers SET isVIP = ? WHERE userID = ?";
	try (Connection con = DatabaseConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {
	    ps.setBoolean(1, isVIP); // Đặt giá trị kiểu Boolean (true -> 1, false -> 0)
	    ps.setString(2, userID);
	    ps.executeUpdate();
	}
    }

}
