package com.spa.dao;

import com.spa.dto.Appointment;
import com.spa.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDAO {

    public List<Appointment> getAllConsultations(String staffID) throws Exception {
        List<Appointment> consultations = new ArrayList<>();
        String sql = "SELECT a.appointmentID, u.fullName AS userName, s.serviceName, a.appointmentDate, a.status, u.isVIP, "
                + "st.userID AS staffID, st.fullName AS staffName, a.note "
                + "FROM tblAppointments a "
                + "JOIN tblUsers u ON a.userID = u.userID "
                + "JOIN tblServices s ON a.serviceID = s.serviceID "
                + "LEFT JOIN tblUsers st ON a.staffID = st.userID "
                + "WHERE a.status IN ('Pending', 'Completed')"
                + "AND a.staffID = ?";

        try ( Connection con = DatabaseConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, staffID);
            try ( ResultSet rs = ps.executeQuery()) {
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
                    consultations.add(appointment);
                }
            }
        }
        return consultations;
    }

    public List<Appointment> getAllAppointmentsOfStaffs() throws Exception {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT a.appointmentID, u.fullName AS userName, s.serviceName, a.appointmentDate, a.status, u.isVIP, "
                + "st.userID AS staffID, st.fullName AS staffName, a.note "
                + "FROM tblAppointments a "
                + "JOIN tblUsers u ON a.userID = u.userID "
                + "JOIN tblServices s ON a.serviceID = s.serviceID "
                + "JOIN tblUsers st ON a.staffID = st.userID"; // Thay LEFT JOIN bằng INNER JOIN để chỉ lấy khi có staff
        try ( Connection con = DatabaseConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
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
}
