package com.example.rochet.eatsmart.Model;

import java.io.Serializable;

public class Meal implements Serializable {
    private String id;
    private String name;
    private String image;
    private String description;
    private Integer price;
    private Integer categoryId;

    public Meal() {
    }

    public Meal(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Meal(String id, String name, String image, String description, Integer price, Integer categoryId) {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
