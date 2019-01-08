package com.example.rochet.eatsmart.Model;

import java.io.Serializable;

public class Meal implements Serializable {
    private String id;
    private String name;
    private String image;
    private String description="description du meal";
    private String price = "1234";
    private String categoryId = "1111";

    public Meal() {
    }

    public Meal(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Meal(String id, String name, String image, String description, String price, String categoryId) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
