package com.spa.dto;

public class ConsumableDTO {

    private String consumableID;
    private String name;
    private String unit;
    private int stock;
    private boolean status;

    public ConsumableDTO() {
    }

    public ConsumableDTO(String consumableID, String name, String unit, int stock, boolean status) {
        this.consumableID = consumableID;
        this.name = name;
        this.unit = unit;
        this.stock = stock;
        this.status = status;
    }

    public String getConsumableID() {
        return consumableID;
    }

    public void setConsumableID(String consumableID) {
        this.consumableID = consumableID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
