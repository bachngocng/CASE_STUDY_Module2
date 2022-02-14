package com.codegym.model;

import java.io.*;


public class Bill implements Serializable {
    private String id;
    private Cart cart = new Cart();
    private long money;

    public Bill() {
    }

    public Bill(String id, Cart cart, int quantity, long money) throws IOException, ClassNotFoundException {
        this.id = id;
        this.cart = cart;
        this.money = cart.totalMoney();
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Bill{" +
                "id='" + id + '\'' +
                ", product=" + cart +
                "money= "+ money +
                '}';
    }
}
