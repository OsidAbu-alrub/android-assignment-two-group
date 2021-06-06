package com.abualrub.androidassignmenttwogroup.domain;

import java.util.ArrayList;

// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public class Order {
    private ArrayList<Cart> carts;

    public Order(){
        carts = new ArrayList<>();
    }

    public Order(ArrayList<Cart> carts) {
        this.carts = carts;
    }

    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<Cart> carts) {
        this.carts = carts;
    }
}
