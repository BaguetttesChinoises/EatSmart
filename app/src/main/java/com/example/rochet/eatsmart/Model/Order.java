package com.example.rochet.eatsmart.Model;

public class Order {
    private String ProductId;
    private String ProductName;
    private String Quantity;
    private Integer Price;

    public Order() {
    }

    public Order(String productId, String productName, String quantity, Integer price) {
        this.ProductId = productId;
        this.ProductName = productName;
        this.Quantity = quantity;
        this.Price = price;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        this.ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        this.ProductName = productName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        this.Quantity = quantity;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        this.Price = price;
    }
}
