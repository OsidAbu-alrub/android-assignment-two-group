package com.abualrub.androidassignmenttwogroup.domain;

import com.abualrub.androidassignmenttwogroup.utils.IConstants;


// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
import java.util.HashMap;

public class Cart implements IConstants {
    private HashMap<Integer,Item> items;
    private String date;
    public Cart(){
        items = new HashMap<Integer,Item>();
    }

    public String calculateTax(){
        double tax = 0;
        for(Item item : items.values()){
            tax += item.getPrice()*TAX_RATE;
        }
        return String.format("Taxes: %.2f",tax);
    }

    public String calculatePriceWithTax(){
        double priceWithTax = 0;
        for(Item item : items.values()){
            priceWithTax += (item.getPrice()*TAX_RATE)+item.getPrice();
        }
        return String.format("Total With Tax: %.2f",priceWithTax);
    }

    public String calculatePriceWithoutTax(){
        double priceWithoutTax = 0;
        for(Item item : items.values()){
            priceWithoutTax += item.getPrice();
        }
        return String.format("Total Without Tax: %.2f",priceWithoutTax);
    }

    public Cart(HashMap<Integer,Item> items) {
        this.items = items;
    }

    public HashMap<Integer,Item> getItems() {
        return items;
    }

    public void setItems(HashMap<Integer,Item> items) {
        this.items = items;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return date;
    }
}
