package com.abualrub.androidassignmenttwogroup.domain;

import com.abualrub.androidassignmenttwogroup.utils.Category;

public class Item {
    private String title;
    private int quantity;
    private double rating;
    private double price;
    private Category category;

    public Item(){

    }

    public Item(String title, int quantity, double rating, double price,Category category) {
        this.title = title;
        this.quantity = quantity;
        this.rating = rating;
        this.price = price;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public Category getCategory(){
        return category;
    }

    public void setCategory(Category category){
        this.category = category;
    }
}
