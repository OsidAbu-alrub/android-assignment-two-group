package com.abualrub.androidassignmenttwogroup.domain;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Item> items;
    private double totalPriceAfterTax;
    private double totalPriceBeforeTax;

    public Cart(){

    }

    public Cart(ArrayList<Item> items, double totalPriceAfterTax, double totalPriceBeforeTax) {
        this.items = items;
        this.totalPriceAfterTax = totalPriceAfterTax;
        this.totalPriceBeforeTax = totalPriceBeforeTax;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public double getTotalPriceAfterTax() {
        return totalPriceAfterTax;
    }

    public void setTotalPriceAfterTax(double totalPriceAfterTax) {
        this.totalPriceAfterTax = totalPriceAfterTax;
    }

    public double getTotalPriceBeforeTax() {
        return totalPriceBeforeTax;
    }

    public void setTotalPriceBeforeTax(double totalPriceBeforeTax) {
        this.totalPriceBeforeTax = totalPriceBeforeTax;
    }
}
