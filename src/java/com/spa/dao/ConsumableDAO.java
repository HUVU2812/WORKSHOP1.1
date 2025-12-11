package com.spa.dao;

import com.spa.dto.ConsumableDTO;
import com.spa.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsumableDAO {

    private Connection conn;

    public ConsumableDAO() {
    }

    public List<ConsumableDTO> getAllConsumables() throws SQLException {
        List<ConsumableDTO> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT consumableID, name, unit, stock, status FROM tblConsumable";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ConsumableDTO c = new ConsumableDTO(
                        rs.getString("consumableID"),
                        rs.getString("name"),
                        rs.getString("unit"),
                        rs.getInt("stock"),
                        rs.getBoolean("status")
                );
                list.add(c);
            }
        } catch (Exception e) {
        }

        return list;
    }
    
     public boolean insertConsumable(String id, String name, String unit, int stock) throws Exception {
        String sql = "INSERT INTO tblConsumable (consumableID, name, unit, stock, status) "+
            "VALUES (?, ?, ?, ?, 1)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, id);
            stm.setString(2, name);
            stm.setString(3, unit);
            stm.setInt(4, stock);

            return stm.executeUpdate() > 0;
        }
    }

    public boolean getConsumableByID(String id) throws SQLException {
        for (ConsumableDTO c : getAllConsumables()) {
            if(c.getConsumableID().equalsIgnoreCase(id)){
                return true;
            }
        }
        return false;
    }
    
    public ConsumableDTO getOneConsumableByID(String id) throws SQLException {
        for (ConsumableDTO c : getAllConsumables()) {
            if(c.getConsumableID().equalsIgnoreCase(id)){
                return c;
            }
        }
        return null;
    }
    
    public boolean updateConsumable(String id, String name, String unit, int stock) throws Exception {
    boolean check = false;
    String sql = "UPDATE tblConsumable "
               + "SET name=?, unit=?, stock=? "
               + "WHERE consumableID=?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stm = conn.prepareStatement(sql)) {

        stm.setString(1, name);
        stm.setString(2, unit);
        stm.setInt(3, stock);
        stm.setString(4, id);

        check = stm.executeUpdate() > 0;
    }
    return check;
}

    
}
