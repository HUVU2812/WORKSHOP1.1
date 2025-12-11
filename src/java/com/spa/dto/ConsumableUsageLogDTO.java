package com.spa.dto;

import java.util.Date;



public class ConsumableUsageLogDTO {

    private int logID;
    private String consumableID;
    private String consumableName;   // join
    private String appointmentID;
    private int quantityUsed;
    private String usedAt;
    private String staffID;
    private String staffName;        // join

    public ConsumableUsageLogDTO() {}

    public ConsumableUsageLogDTO(int logID, String consumableID, String consumableName,
                                 String appointmentID, int quantityUsed, String usedAt,
                                 String staffID, String staffName) {
        this.logID = logID;
        this.consumableID = consumableID;
        this.consumableName = consumableName;
        this.appointmentID = appointmentID;
        this.quantityUsed = quantityUsed;
        this.usedAt = usedAt;
        this.staffID = staffID;
        this.staffName = staffName;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public String getConsumableID() {
        return consumableID;
    }

    public void setConsumableID(String consumableID) {
        this.consumableID = consumableID;
    }

    public String getConsumableName() {
        return consumableName;
    }

    public void setConsumableName(String consumableName) {
        this.consumableName = consumableName;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public int getQuantityUsed() {
        return quantityUsed;
    }

    public void setQuantityUsed(int quantityUsed) {
        this.quantityUsed = quantityUsed;
    }

    public String getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(String usedAt) {
        this.usedAt = usedAt;
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
}
