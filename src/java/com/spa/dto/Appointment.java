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
public class Appointment {

    private String appointmentID;
    private String appointmentDate;
    private String status;
    private String userName;
    private String serviceName;
    private String staffID;
    private String staffName;
    private String note;
    private Boolean isVIP;

    public Appointment() {
    }

    public Appointment(String appointmentID, String userName, String serviceName, String appointmentDate, String status, String staffID, String staffName, String note, Boolean isVIP) {
        this.appointmentID = appointmentID;
        this.userName = userName;
        this.serviceName = serviceName;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.staffID = staffID;
        this.staffName = staffName;
        this.note = note;
	this.isVIP = isVIP;
    }

    public Boolean getIsVIP() {
	return isVIP;
    }

    public void setIsVIP(Boolean isVIP) {
	this.isVIP = isVIP;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
