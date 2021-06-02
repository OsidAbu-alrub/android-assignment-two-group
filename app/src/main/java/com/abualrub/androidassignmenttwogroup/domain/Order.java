package com.abualrub.androidassignmenttwogroup.domain;

import java.util.ArrayList;

public class Order {
    private Cart cart;
    private User user;

    public Order(){

    }

    public Order(Cart cart, User user) {
        this.cart = cart;
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
