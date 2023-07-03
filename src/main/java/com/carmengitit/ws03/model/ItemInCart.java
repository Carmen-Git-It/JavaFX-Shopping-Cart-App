package com.carmengitit.ws03.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ItemInCart {
    private StringProperty name = new SimpleStringProperty();

    private DoubleProperty quantity = new SimpleDoubleProperty();

    public ItemInCart() {
        name.setValue("");
        quantity.setValue(0);
    }

    public ItemInCart(String name, double quantity) {
        this.name.setValue(name);
        this.quantity.setValue(quantity);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getQuantity() {
        return quantity.get();
    }

    public DoubleProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity.set(quantity);
    }
}
