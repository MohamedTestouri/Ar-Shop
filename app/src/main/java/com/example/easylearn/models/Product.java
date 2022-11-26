package com.example.easylearn.models;

public class Product {
    private int id;
    private String name;
    private float price;
    private String description;
    private String url;
    private String image;

    public Product() {
    }

    public Product(int id, String name, float price, String description, String url, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.url = url;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
