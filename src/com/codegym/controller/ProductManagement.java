package com.codegym.controller;

import com.codegym.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductManagement implements GeneralManagement<Product>, ReadFile, WriteFile {
    private List<Product> products = new ArrayList<>();
    private static ProductManagement productManagement;
    public ProductManagement() {
        File file = new File("product.txt");
        if (file.exists()) {
            try {
                readFile("product.txt");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public int size() {
        return products.size();
    }

    //Tìm theo mã sản phẩm
    public int findProductById(String id) {
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    //Tìm theo tên sản phẩm
    public int findProductByName(String name) {
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

    //Sắp xếp theo mã sản phẩm : chiều hỏi Quân???
    public void sortProductById() {
        for (int i = 0; i < products.size(); i++) {
            for (int j = products.size() -1; j > i; j--) {
                if (products.get(j).getId().equals(products.get(j - 1).getId())) {
                    Product temp = products.get(j);
                    products.set(j, products.get(j - 1));
                    products.set(j - 1, temp);
                }
            }
        }
    }

    //Sắp xếp theo giá: thấp đến cao
    public void sortProductByAscendingPrice() {
        products.sort(new ProductComparator());
    }

    //Hiển thị
    @Override
    public void displayAll() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    //Thêm
    @Override
    public void addNew(Product product) {
        products.add(product);
    }

    //Sửa
    @Override
    public void updateById(String id, Product product) {
        int index = findProductById(id);
        products.set(index, product);
    }

    //Xóa
    @Override
    public boolean deleteById(String id) {
        int index = findProductById(id);
        if (index != -1) {
            products.remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Product getById(String id) {
        int index = findProductById(id);
        return products.get(index);
    }

    public Product getByName(String name) {
        int index = findProductByName(name);
        return this.products.get(index);
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.products = (List<Product>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.products);
    }

    public static ProductManagement getInstance(){
        if(productManagement == null){
            productManagement = new ProductManagement();
        }
        return productManagement;
    }
}
