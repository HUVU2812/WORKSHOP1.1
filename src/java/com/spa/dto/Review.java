/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.dto;

/**
 *
 * @author Admin
 */
public class Review {

    private String reviewID;
    private String userID;
    private String serviceID;
    private int rating;
    private String comments;
    private String serviceName;
    private String userName;
    private boolean status;
    private Boolean isVIP;

    public Review() {
    }

    public Review(String reviewID, String userID, String serviceID, int rating, String comments, String serviceName, String userName, boolean status, Boolean isVIP) {
	this.reviewID = reviewID;
	this.userID = userID;
	this.serviceID = serviceID;
	this.rating = rating;
	this.comments = comments;
	this.serviceName = serviceName;
	this.userName = userName;
	this.status = status;
	this.isVIP = isVIP;
    }

    public Boolean getIsVIP() {
	return isVIP;
    }

    public void setIsVIP(Boolean isVIP) {
	this.isVIP = isVIP;
    }

    public boolean isStatus() {
	return status;
    }

    public void setStatus(boolean status) {
	this.status = status;
    }

    public String getServiceName() {
	return serviceName;
    }

    public void setServiceName(String serviceName) {
	this.serviceName = serviceName;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getReviewID() {
	return reviewID;
    }

    public void setReviewID(String reviewID) {
	this.reviewID = reviewID;
    }

    public String getUserID() {
	return userID;
    }

    public void setUserID(String userID) {
	this.userID = userID;
    }

    public String getServiceID() {
	return serviceID;
    }

    public void setServiceID(String serviceID) {
	this.serviceID = serviceID;
    }

    public int getRating() {
	return rating;
    }

    public void setRating(int rating) {
	this.rating = rating;
    }

    public String getComments() {
	return comments;
    }

    public void setComments(String comments) {
	this.comments = comments;
    }

}
