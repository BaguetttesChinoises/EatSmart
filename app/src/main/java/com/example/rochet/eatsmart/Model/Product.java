package com.example.rochet.eatsmart.Model;

public class Product {

    private int id;
    private String title;
    private String description;
    private double rating;
    private double price;
    // Use an int because we use static images saved into the res/drawable folder
    private int image;

    public Product(int id, String title, String description, double rating, double price, int image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
