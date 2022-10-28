package com.example.petcare.model;

import java.io.Serializable;

public class Category implements Serializable {
    private String imageCategory;
    private String nameCategory;

    public Category() {
    }

    public Category(String imageCategory, String nameCategory) {
        this.imageCategory = imageCategory;
        this.nameCategory = nameCategory;
    }

    public String getImageCategory() {
        return imageCategory;
    }

    public void setImageCategory(String imageCategory) {
        this.imageCategory = imageCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

}
