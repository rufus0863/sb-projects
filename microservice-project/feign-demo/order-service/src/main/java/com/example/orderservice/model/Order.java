package com.example.orderservice.model;

public class Order {
    private Long id;
    private Long userId;
    private String product;
    private double price;

    public Order() {}
    public Order(Long id, Long userId, String product, double price) {
        this.id = id;
        this.userId = userId;
        this.product = product;
        this.price = price;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getProduct() { return product; }
    public double getPrice() { return price; }
}