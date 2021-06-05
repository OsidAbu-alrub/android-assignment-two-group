package com.abualrub.androidassignmenttwogroup.domain;

import com.abualrub.androidassignmenttwogroup.utils.Category;

// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public class Item {
    private String title;
    private double rating;
    private double price;
    private Category category;
    private int icon;

    public Item(){

    }

    public Item(String title, double rating, double price,Category category,int icon) {
        this.title = title;
        this.rating = rating;
        this.price = price;
        this.category = category;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getIcon(){
        return this.icon;
    }

    public void setIcon(int icon){
        this.icon= icon;
    }
}
