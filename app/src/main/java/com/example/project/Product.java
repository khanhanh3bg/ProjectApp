package com.example.project;

public class Product {
    int id;
    String name, url;
    int quantity;

    public Product(int id, String name, String url, int quantity) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.quantity = quantity;
    }

    public Product() {
    }

    public Product(String name, String url, int quantity) {
        this.name = name;
        this.url = url;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getQuantity() {
        return quantity;
    }
}
