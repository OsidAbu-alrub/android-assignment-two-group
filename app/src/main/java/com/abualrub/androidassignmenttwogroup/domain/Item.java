package com.abualrub.androidassignmenttwogroup.domain;

import com.abualrub.androidassignmenttwogroup.R;
import com.abualrub.androidassignmenttwogroup.utils.Category;

public class Item {
    private String title;
    private int quantity;
    private double rating;
    private double price;
    private Category category;
    private int categoryID;
    int icon;

    public Item(){

    }
    public static final Item[] items={
            new Item("T-shirt",4.5,34, R.drawable.tshirt),
            new Item("Adidas shoe",4,230, R.drawable.adidasshoes),
            new Item("Ball",4.8,22, R.drawable.ball)

    };

    public Item(String title,double rating, double price,int icon) {
        this.title = title;
        this.rating = rating;
        this.price = price;
        this.icon = icon;
    }
    public Item(String title,double rating, double price,int icon,int categoryID) {
        this.title = title;
        this.rating = rating;
        this.price = price;
        this.icon = icon;
        this.categoryID=categoryID;
    }

    public Item(String title, int quantity, double rating, double price, Category category) {
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

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public Category getCategory(){
        return category;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
