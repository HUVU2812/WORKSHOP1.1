/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.dao;

import com.spa.dto.Review;
import com.spa.dto.Service;
import com.spa.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ReviewDAO {

    public boolean addReview(Review review) throws Exception {
	String sql = "INSERT INTO tblReviews (reviewID, userID, serviceID, rating, comments, status) VALUES (?, ?, ?, ?, ?, 1)";

	try (Connection con = DatabaseConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {
	    ps.setString(1, review.getReviewID());
	    ps.setString(2, review.getUserID());
	    ps.setString(3, review.getServiceID());
	    ps.setInt(4, review.getRating());
	    ps.setString(5, review.getComments());

	    return ps.executeUpdate() > 0;
	}
    }

    public List<Review> getAllReviews() throws Exception {
	List<Review> reviewList = new ArrayList<>();
	String sql = "SELECT r.reviewID, r.userID, r.serviceID, r.rating, r.comments, u.isVIP, "
		+ "u.fullName AS userName, s.serviceName AS serviceName, r.status "
		+ "FROM tblReviews r "
		+ "JOIN tblUsers u ON r.userID = u.userID "
		+ "JOIN tblServices s ON r.serviceID = s.serviceID";

	try (Connection con = DatabaseConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery()) {
	    while (rs.next()) {
		reviewList.add(new Review(
			rs.getString("reviewID"),
			rs.getString("userID"),
			rs.getString("serviceID"),
			rs.getInt("rating"),
			rs.getString("comments"),
			rs.getString("serviceName"),
			rs.getString("userName"),
			rs.getBoolean("status"),
			rs.getBoolean("isVIP")
		));
	    }
	}
	return reviewList;
    }

    public boolean cancelReview(String reviewID) throws Exception {
	String sql = "UPDATE tblReviews SET status = '0' WHERE reviewID = ?";
	try (Connection con = DatabaseConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {

	    ps.setString(1, reviewID);
	    int rowsAffected = ps.executeUpdate();
	    return rowsAffected > 0;
	}
    }

    public List<Review> getActiveReviews() throws Exception {
	List<Review> reviewLists = new ArrayList<>();
	String sql = "SELECT r.reviewID, r.userID, u.fullName, r.serviceID, s.serviceName, r.rating, r.comments, r.status, u.isVIP "
		+ "FROM tblReviews r "
		+ "JOIN tblUsers u ON r.userID = u.userID "
		+ "JOIN tblServices s ON r.serviceID = s.serviceID "
		+ "WHERE r.status = 1"; // Chỉ lấy review có status = 1

	try (Connection conn = DatabaseConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery()) {

	    while (rs.next()) {
		boolean status = rs.getBoolean("status"); // Lấy giá trị status từ DB
		Review review = new Review(
			rs.getString("reviewID"),
			rs.getString("userID"),
			rs.getString("serviceID"),
			rs.getInt("rating"),
			rs.getString("comments"),
			rs.getString("serviceName"),
			rs.getString("fullName"),
			status,
			rs.getBoolean("isVIP")
		);
		reviewLists.add(review);
	    }
	}
	return reviewLists;
    }
    public List<Review> getActiveReviews1() throws Exception {
	List<Review> reviewLists1 = new ArrayList<>();
	String sql = "SELECT r.reviewID, r.userID, u.fullName, r.serviceID, s.serviceName, r.rating, r.comments, r.status, u.isVIP "
		+ "FROM tblReviews r "
		+ "JOIN tblUsers u ON r.userID = u.userID "
		+ "JOIN tblServices s ON r.serviceID = s.serviceID "
		+ "WHERE r.status = 1 AND s.status = 0"; // Chỉ lấy review có status = 1 va service co status bang 0

	try (Connection conn = DatabaseConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery()) {

	    while (rs.next()) {
		boolean status = rs.getBoolean("status"); // Lấy giá trị status từ DB
		Review review = new Review(
			rs.getString("reviewID"),
			rs.getString("userID"),
			rs.getString("serviceID"),
			rs.getInt("rating"),
			rs.getString("comments"),
			rs.getString("serviceName"),
			rs.getString("fullName"),
			status,
			rs.getBoolean("isVIP")
		);
		reviewLists1.add(review);
	    }
	}
	return reviewLists1;
    }
}
