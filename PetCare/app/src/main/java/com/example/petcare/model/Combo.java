package com.example.petcare.model;
public class Combo {
    private int comboImage;
    private String comboName;
    private String description;
    private double price;

    public Combo() {
    }

    public Combo(int comboImage, String comboName, String description, double price) {
        this.comboImage = comboImage;
        this.comboName = comboName;
        this.description = description;
        this.price = price;
    }

    public String getComboName() {
        return comboName;
    }

    public int getComboImage() {
        return comboImage;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getDescription() {
        return description;
    }

    public void setComboImage(int comboImage) {
        this.comboImage = comboImage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Combo{" +
                "comboName='" + comboName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}

