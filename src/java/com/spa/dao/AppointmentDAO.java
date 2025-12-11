package com.spa.dao;

import com.spa.dto.Appointment;
import com.spa.dto.User;
import com.spa.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    // Đặt lịch hẹn
    public boolean bookAppointment(String userID, String serviceID, String appointmentDate) throws Exception {
	String sql = "INSERT INTO tblAppointments (appointmentID, userID, serviceID, appointmentDate, status) "
		+ "VALUES (?, ?, ?, ?, 'Pending')";
	try (Connection con = DatabaseConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {

	    String appointmentID = "apt" + System.currentTimeMillis(); // Tạo ID duy nhất

	    ps.setString(1, appointmentID);
	    ps.setString(2, userID);
	    ps.setString(3, serviceID);
	    ps.setString(4, appointmentDate);

	    return ps.executeUpdate() > 0;
	}
    }

    public List<Appointment> getAllAppointments() throws Exception {
	List<Appointment> list = new ArrayList<>();
	String sql = "SELECT a.appointmentID, u.fullName AS userName, s.serviceName, a.appointmentDate, a.status, u.isVIP, "
		+ "st.userID AS staffID, st.fullName AS staffName, a.note "
		+ "FROM tblAppointments a "
		+ "JOIN tblUsers u ON a.userID = u.userID "
		+ "JOIN tblServices s ON a.serviceID = s.serviceID "
		+ "LEFT JOIN tblUsers st ON a.staffID = st.userID"; // Lấy cả các lịch hẹn chưa có nhân viên

	try (Connection con = DatabaseConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery()) {

	    while (rs.next()) {
		Appointment appointment = new Appointment(
			rs.getString("appointmentID"),
			rs.getString("userName"),
			rs.getString("serviceName"),
			rs.getString("appointmentDate"),
			rs.getString("status"),
			rs.getString("staffID"),
			rs.getString("staffName"),
			rs.getString("note"),
			rs.getBoolean("isVIP")
		);
		list.add(appointment);
	    }
	}
	return list;
    }

    public boolean cancelAppointment(String appointmentID) throws Exception {
	String sql = "UPDATE tblAppointments SET status = 'Cancelled' WHERE appointmentID = ?";
	try (Connection con = DatabaseConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {

	    ps.setString(1, appointmentID);
	    int rowsAffected = ps.executeUpdate();
	    return rowsAffected > 0;
	}
    }

    public boolean completeAppointment(String appointmentID) throws Exception {
	String sql = "UPDATE tblAppointments SET status = 'Completed' WHERE appointmentID = ?";
	try (Connection con = DatabaseConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {

	    ps.setString(1, appointmentID);
	    int rowsAffected = ps.executeUpdate();
	    return rowsAffected > 0;
	}
    }

    public void assignStaffToAppointment(String appointmentID, String staffID) throws Exception {
	String sql = "UPDATE tblAppointments SET staffID = ? WHERE appointmentID = ?";

	try (Connection con = DatabaseConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {
	    ps.setString(1, staffID);
	    ps.setString(2, appointmentID);
	    ps.executeUpdate();
	}
    }

    public List<Appointment> getAppointmentsByUser(String userID) throws Exception {
	List<Appointment> list = new ArrayList<>();
	String sql = "SELECT a.appointmentID, s.serviceName, a.appointmentDate, a.status, a.note, u.isVIP "
		+ "FROM tblAppointments a "
		+ "JOIN tblServices s ON a.serviceID = s.serviceID "
		+ "WHERE a.userID = ? AND a.status = 'Pending'"; // Chỉ lấy cuộc hẹn của user đang đăng nhập với trạng thái 'Pending'

	try (Connection con = DatabaseConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {
	    ps.setString(1, userID);
	    try (ResultSet rs = ps.executeQuery()) {
		while (rs.next()) {
		    Appointment appointment = new Appointment(
			    rs.getString("appointmentID"),
			    null, // Không cần userName
			    rs.getString("serviceName"),
			    rs.getString("appointmentDate"),
			    rs.getString("status"),
			    null, // Không cần staffID
			    null, // Không cần staffName
			    rs.getString("note"),
			    rs.getBoolean("isVIP")
		    );
		    list.add(appointment);
		}
	    }
	}
	return list;
    }

    public boolean updateAppointmentNote(String appointmentID, String note) throws Exception {
	String sql = "UPDATE tblAppointments SET note = ? WHERE appointmentID = ?";

	try (Connection con = DatabaseConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {
	    ps.setString(1, note);
	    ps.setString(2, appointmentID);
	    return ps.executeUpdate() > 0;
	}
    }
}
