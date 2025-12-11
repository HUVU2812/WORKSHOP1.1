/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spa.dao;

/**
 *
 * @author nguye
 */
import com.spa.dto.ConsumableUsageLogDTO;
import com.spa.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsageLogDAO {

    public UsageLogDAO() {
    }

    public List<ConsumableUsageLogDTO> getLogsByAppointment(String appointmentID) throws SQLException {
        List<ConsumableUsageLogDTO> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql
                    = "SELECT l.logID, l.consumableID, c.name AS consumableName,"
                    + " l.appointmentID, l.quantityUsed, l.usedAt,"
                    + " l.staffID, u.fullName AS staffName"
                    + " FROM tblConsumableUsageLog "
                    + " JOIN tblConsumable c ON l.consumableID = c.consumableID"
                    + " JOIN tblUsers u ON l.staffID = u.userID"
                    + " WHERE l.appointmentID = ?"
                    + " ORDER BY l.usedAt DESC";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, appointmentID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ConsumableUsageLogDTO log = new ConsumableUsageLogDTO(
                        rs.getInt("logID"),
                        rs.getString("consumableID"),
                        rs.getString("consumableName"),
                        rs.getString("appointmentID"),
                        rs.getInt("quantityUsed"),
                        rs.getString("usedAt"),
                        rs.getString("staffID"),
                        rs.getString("staffName")
                );
                list.add(log);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<ConsumableUsageLogDTO> getAllUsageLogs() throws Exception {
        List<ConsumableUsageLogDTO> list = new ArrayList<>();

        String sql
                = "SELECT "
                + " log.logID,"
                + " log.consumableID,"
                + " c.name AS consumableName,"
                + " log.appointmentID,"
                + " log.quantityUsed,"
                + " log.usedAt,"
                + " a.staffID,"
                + " u.fullName AS staffName"
                + " FROM tblConsumableUsageLog AS log"
                + " JOIN tblConsumable AS c"
                + " ON log.consumableID = c.consumableID"
                + " JOIN tblAppointments AS a"
                + " ON log.appointmentID = a.appointmentID"
                + " JOIN tblUsers AS u"
                + " ON a.staffID = u.userID"
                + " ORDER BY log.usedAt DESC";

        try ( Connection conn = DatabaseConnection.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);  ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                list.add(new ConsumableUsageLogDTO(
                        rs.getInt("logID"),
                        rs.getString("consumableID"),
                        rs.getString("consumableName"),
                        rs.getString("appointmentID"),
                        rs.getInt("quantityUsed"),
                        rs.getString("usedAt"),
                        rs.getString("staffID"),
                        rs.getString("staffName")
                ));
            }
        }

        return list;
    }

    // ... (code cũ giữ nguyên)
    public boolean insertConsumableUsageLog(String consumableID, String appointmentID, int quantityUsed, String usedAt) throws Exception {
        String sql = "INSERT INTO tblConsumableUsageLog (consumableID, appointmentID, quantityUsed, usedAt) "
                + "VALUES (?, ?, ?, ?)";

        try ( Connection conn = DatabaseConnection.getConnection();  PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, consumableID);
            stm.setString(2, appointmentID);
            stm.setInt(3, quantityUsed);
            stm.setDate(4, Date.valueOf(usedAt));

            int rowsAffected = stm.executeUpdate();
            
            return rowsAffected > 0;
        }
    }

// Method mới: Update stock (có thể dùng cho trừ/thêm)
    public boolean updateStock(String consumableID, int quantityChange) throws Exception {
        String sql = "UPDATE tblConsumable SET stock = stock + ? WHERE consumableID = ? AND stock + ? >= 0";
        try ( Connection conn = DatabaseConnection.getConnection();  PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, quantityChange);
            stm.setString(2, consumableID);
            stm.setInt(3, quantityChange);  // Check stock không âm
            return stm.executeUpdate() > 0;
        }
    }
}
