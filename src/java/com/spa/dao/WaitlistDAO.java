package com.spa.dao;

import com.spa.dto.WaitlistEntry;
import com.spa.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaitlistDAO {

    public boolean addToWaitlist(String userID, String serviceID, String appointmentDate) throws Exception {
        String sql = "INSERT INTO tblWaitList (waitID, userID, serviceID, appointmentDate, status) VALUES (?, ?, ?, ?, 'waiting')";
        try ( Connection con = DatabaseConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            String waitID = "w" + System.currentTimeMillis();
            ps.setString(1, waitID);
            ps.setString(2, userID);
            ps.setString(3, serviceID);
            ps.setString(4, appointmentDate);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * List with optional filters: dateStr in yyyy-MM-dd, and serviceID (or
     * "all").
     */
    public List<WaitlistEntry> find(String dateStr, String serviceID) throws Exception {
        String sql = "SELECT waitID, userID, serviceID, appointmentDate, status FROM tblWaitList ORDER BY appointmentDate ASC";
        List<WaitlistEntry> list = new ArrayList<>();
        try ( Connection con = DatabaseConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String apptDate = rs.getString("appointmentDate");
                String sid = rs.getString("serviceID");
                boolean ok = true;
                if (dateStr != null && !dateStr.isEmpty()) {
                    ok = apptDate != null && apptDate.startsWith(dateStr); // matches yyyy-MM-dd
                }
                if (ok && serviceID != null && !serviceID.isEmpty() && !"all".equalsIgnoreCase(serviceID)) {
                    ok = sid != null && sid.equals(serviceID);
                }
                if (ok) {
                    list.add(new WaitlistEntry(
                            rs.getString("waitID"),
                            rs.getString("userID"),
                            sid,
                            apptDate,
                            rs.getString("status")
                    ));
                }
            }
        }
        return list;
    }

    /**
     * Approve: add to tblAppointments with status 'noticed', then delete from
     * waitlist.
     */
    public boolean approve(String waitID) throws Exception {
        String select = "SELECT userID, serviceID, appointmentDate FROM tblWaitList WHERE waitID=?";
        try ( Connection con = DatabaseConnection.getConnection()) {
            con.setAutoCommit(false);
            try ( PreparedStatement psSel = con.prepareStatement(select)) {
                psSel.setString(1, waitID);
                try ( ResultSet rs = psSel.executeQuery()) {
                    if (!rs.next()) {
                        con.rollback();
                        return false;
                    }
                    String userID = rs.getString("userID");
                    String serviceID = rs.getString("serviceID");
                    String appointmentDate = rs.getString("appointmentDate");

                    String insert = "INSERT INTO tblAppointments (appointmentID, userID, serviceID, appointmentDate, status) VALUES (?, ?, ?, ?, 'noticed')";
                    try ( PreparedStatement psIns = con.prepareStatement(insert)) {
                        String appointmentID = "apt" + System.currentTimeMillis();
                        psIns.setString(1, appointmentID);
                        psIns.setString(2, userID);
                        psIns.setString(3, serviceID);
                        psIns.setString(4, appointmentDate);
                        int ins = psIns.executeUpdate();
                        if (ins <= 0) {
                            con.rollback();
                            return false;
                        }
                    }

                    try ( PreparedStatement psDel = con.prepareStatement("DELETE FROM tblWaitList WHERE waitID=?")) {
                        psDel.setString(1, waitID);
                        int del = psDel.executeUpdate();
                        if (del <= 0) {
                            con.rollback();
                            return false;
                        }
                    }

                    con.commit();
                    return true;
                }
            } catch (Exception ex) {
                con.rollback();
                throw ex;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }
}
