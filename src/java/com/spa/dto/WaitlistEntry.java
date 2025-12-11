package com.spa.dto;

public class WaitlistEntry {
    private String waitID;
    private String userID;
    private String serviceID;
    private String appointmentDate; // yyyy-MM-dd HH:mm
    private String status; // waiting | noticed

    public WaitlistEntry() {}

    public WaitlistEntry(String waitID, String userID, String serviceID, String appointmentDate, String status) {
        this.waitID = waitID;
        this.userID = userID;
        this.serviceID = serviceID;
        this.appointmentDate = appointmentDate;
        this.status = status;
    }

    public String getWaitID() { return waitID; }
    public void setWaitID(String waitID) { this.waitID = waitID; }

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    public String getServiceID() { return serviceID; }
    public void setServiceID(String serviceID) { this.serviceID = serviceID; }

    public String getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(String appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
