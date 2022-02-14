package com.codegym.model;

import com.codegym.controller.ProductManagement;
import com.codegym.controller.ReadFile;
import com.codegym.controller.WriteFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart implements ReadFile, WriteFile, Serializable {
    private List<Product> cart = new ArrayList<>();
    private ProductManagement productManagement = ProductManagement.getInstance();
    private User user;

    public Cart(){
        File file = new File("yourCart.txt");
        if(file.exists()){
            try{
                readFile("yourCart.txt");
            }
            catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    public List<Product> getCart() {
        return cart;
    }

    public int size() {
        return cart.size();
    }

    public void addNewItem(String name) {
        cart.add(searchProduct(name));
    }

    public void displayCart() {
        for (Product product : cart) {
            System.out.println(product);
        }
    }

    public void removeItem(String name){
        cart.remove(searchProduct(name));
    }

    public Product searchProduct(String name) {
            return productManagement.getByName(name);
    }

    public long totalMoney(){
        long sum = 0;
        for (int i = 0; i < cart.size(); i++) {
            sum+= cart.get(i).getPrice();
        }
        return sum;
    }

    public void removeAllProduct(){
        for (int i = 0; i < cart.size(); i++) {
            for (int j = cart.size(); j > i ; j--) {
                cart.remove(i);
            }
        }
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.cart = (List<Product>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.cart);
    }

    public Cart getInstance(){
        if(cart == null){
            Cart cart = new Cart();
        }
        return (Cart) cart;
    }

}
