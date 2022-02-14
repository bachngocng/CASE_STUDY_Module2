package com.codegym.view;

import com.codegym.model.Product;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.run();
    }
}
