/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.dao;

import com.spa.dto.Service;
import com.spa.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ServiceDAO {

    public boolean createService(Service service) throws Exception, ClassNotFoundException {
	String sql = "INSERT INTO tblServices (serviceID, serviceName, description, price, status) VALUES (?, ?, ?, ?, ?)";
	if (service.getServiceID() == null || service.getServiceID().isEmpty()) {
	    service.setServiceID(generateNewserviceID());
	}
	try (Connection conn = DatabaseConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql)) {
	    ps.setString(1, service.getServiceID());
	    ps.setString(2, service.getServiceName());
	    ps.setString(3, service.getDescription());
	    ps.setDouble(4, service.getPrice());
	    ps.setBoolean(5, service.isStatus());
	    return ps.executeUpdate() > 0;
	}
    }

    public String generateNewserviceID() throws SQLException, ClassNotFoundException {
	String prefix = "svc";
	String sql = "SELECT MAX(serviceID) FROM tblServices";
	int nextID = 1;

	try (Connection conn = DatabaseConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery()) {

	    if (rs.next() && rs.getString(1) != null) {
		String lastID = rs.getString(1);
		nextID = Integer.parseInt(lastID.replaceAll("[^0-9]", "")) + 1;
	    }
	}
	return prefix + nextID;
    }

    public List<Service> getAllServices1() {
	List<Service> services1 = new ArrayList<>();
	try {
	    Connection con = DatabaseConnection.getConnection();
	    String sql = "SELECT serviceID, serviceName, description, price, status FROM tblServices WHERE status = 0";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		String serviceID = rs.getString("serviceID");
		String serviceName = rs.getString("serviceName");
		String description = rs.getString("description");
		double price = rs.getDouble("price");
		boolean status = rs.getBoolean("status");
		services1.add(new Service(serviceID, serviceName, description, price, status));
	    }
	    rs.close();
	    ps.close();
	    con.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return services1;
    }

    public List<Service> getAllServices() {
	List<Service> services = new ArrayList<>();
	try {
	    Connection con = DatabaseConnection.getConnection();
	    String sql = "SELECT serviceID, serviceName, description, price, status FROM tblServices";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		String serviceID = rs.getString("serviceID");
		String serviceName = rs.getString("serviceName");
		String description = rs.getString("description");
		double price = rs.getDouble("price");
		boolean status = rs.getBoolean("status");
		services.add(new Service(serviceID, serviceName, description, price, status));
	    }
	    rs.close();
	    ps.close();
	    con.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return services;
    }

    public Service getServiceById(String serviceID) throws Exception {
	String sql = "SELECT serviceID, serviceName, description, price, status FROM tblServices WHERE serviceID=?";
	try (Connection con = DatabaseConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {
	    ps.setString(1, serviceID);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
		return new Service(
			rs.getString("serviceID"),
			rs.getString("serviceName"),
			rs.getString("description"),
			rs.getDouble("price"),
			rs.getBoolean("status"));
	    }
	}
	return null;
    }

    public boolean updateService(Service service) throws Exception {
	String sql = "UPDATE tblServices SET serviceName = ?, description = ?, price = ?, status= ? WHERE serviceID = ?";
	try (Connection con = DatabaseConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {
	    ps.setString(1, service.getServiceName());
	    ps.setString(2, service.getDescription());
	    ps.setDouble(3, service.getPrice());
	    ps.setBoolean(4, service.isStatus());
	    ps.setString(5, service.getServiceID());

	    return ps.executeUpdate() > 0;
	}
    }

    public boolean deleteService(String serviceID) throws Exception {
	String deleteReviewsSQL = "DELETE FROM tblReviews WHERE serviceID = ?";
	String deleteServiceSQL = "DELETE FROM tblServices WHERE serviceID = ?";

	try (Connection con = DatabaseConnection.getConnection();
		PreparedStatement psReviews = con.prepareStatement(deleteReviewsSQL);
		PreparedStatement psService = con.prepareStatement(deleteServiceSQL)) {

	    // Xóa tất cả các review liên quan đến serviceID
	    psReviews.setString(1, serviceID);
	    psReviews.executeUpdate();

	    // Xóa dịch vụ sau khi đã xóa review
	    psService.setString(1, serviceID);
	    int rowsAffected = psService.executeUpdate();

	    return rowsAffected > 0;
	}
    }

}
