package com.carmengitit.ws03.model;

public class Item {
    private String name;
    private String unit;
    private double quantity;
    private double unitPrice;

    public Item() {
        name = "";
        unit = "";
        quantity = 0;
        unitPrice = 0;
    }

    public Item(String name, String unit, double quantity, double unitPrice) {
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return name;
    }
}
