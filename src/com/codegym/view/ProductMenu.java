package com.codegym.view;

import com.codegym.controller.ProductManagement;
import com.codegym.model.Product;

import java.io.EOFException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class ProductMenu implements Serializable {
    public Scanner scanner = new Scanner(System.in);

    public void run(){
        ProductManagement productManagement = new ProductManagement();
        int choice = -1;
        try{
            productManagement.readFile("product.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        do{
            menu();
            System.out.println("Nhập lựa chọn của bạn");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    showAllProduct(productManagement);
                    break;
                case 2:
                    showCreateProduct(productManagement);
                    break;
                case 3:
                    showUpdatedProduct(productManagement);
                    break;
                case 4:
                    showDeleteProduct(productManagement);
                    break;
                case 5:
                    showfindProduct(productManagement);
                    break;
                case 6:
                    showSortProduct(productManagement);
                    break;
            }
            try{
                productManagement.writeFile("product.txt");
            } catch (IOException e){
                e.printStackTrace();
            }
        }while (choice != 0);
    }

    private void showSortProduct(ProductManagement productManagement) {
        System.out.println("Sắp xếp sản phẩm");
        int choiceSort = -1;
        do {
            sortMenu();
            System.out.println("Nhập lựa chọn của bạn");
            choiceSort = scanner.nextInt();
            switch (choiceSort){
                case 1:
                    System.out.println("Sắp xếp theo mã sản phẩm");
                    productManagement.sortProductById();
                    productManagement.displayAll();
                    break;
                case 2:
                    System.out.println("Sắp xếp theo giá từ thấp đến cao");
                    productManagement.sortProductByAscendingPrice();
                    productManagement.displayAll();
                    break;
            }
        }while (choiceSort != 0);
    }

    private void showfindProduct(ProductManagement productManagement) {
        System.out.println("Tìm kiếm sản phẩm");
        int choiceFind = -1;
        do {
            menuFind();
            System.out.println("Nhập lựa chọn của bạn");
            choiceFind = scanner.nextInt();
            switch (choiceFind){
                case 1:
                    System.out.println("Tìm kiếm theo mã sản phảm");
                    System.out.println("Nhập mã sản phẩm cần tìm");
                    scanner.nextLine();
                    int id = scanner.nextInt();
                    int index = productManagement.findProductById(id);
                    if (index != -1) {
                        System.out.println("Thông tin sản phẩm cần tìm: " + productManagement.getById(id));
                    } else {
                        System.out.println("Không tìm thấy");
                    }
                    break;
                case 2:
                    System.out.println("Tìm kiếm theo tên sản phẩm");
                    System.out.println("Nhập tên sản phẩm cần tìm");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    index = productManagement.findProductByName(name);
                    if (index!=-1) {
                        System.out.println(productManagement.getByName(name));
                    } else {
                        System.out.println("Không tìm thấy");
                    }
                    break;
            }
        }while (choiceFind != 0);
    }

    private void sortMenu() {
        System.out.println("1.Theo mã sản phẩm");
        System.out.println("2.Theo giá tăng dần");
        System.out.println("0.Quay lại");
    }

    private void menuFind() {
        System.out.println("1.Theo mã sản phẩm");
        System.out.println("2.Theo tên sản phẩm");
        System.out.println("0.Quay lại");
    }

    private void showDeleteProduct(ProductManagement productManagement) {
        System.out.println("Xóa sản phẩm");
        System.out.println("Nhập sản phẩm muốn xóa");
        int id = scanner.nextInt();
        boolean isDeleted = productManagement.deleteById(id);
        if (isDeleted){
            System.out.println("Đã xóa");
        } else {
            System.out.println("Xóa lỗi do không có sản phẩm để xóa");
        }
    }

    private void showUpdatedProduct(ProductManagement productManagement) {
        System.out.println("Chỉnh sửa thông tin sản phẩm");
        System.out.println("Nhập mã sản phẩm cần chỉnh sửa thông tin");
        int id = scanner.nextInt();
        int index = productManagement.findProductById(id);
        if(index != -1){
            Product product = inputProductInfo();
            productManagement.updateById(id, product);
            System.out.println("Cập nhật thành công");
        } else {
            System.out.println("Không thể cập nhật do không có sản phẩm nào");
        }
    }

    private void showCreateProduct(ProductManagement productManagement) {
        System.out.println("Thêm sản phẩm mới");
        Product product  = inputProductInfo();
        productManagement.addNew(product);
    }

    private Product inputProductInfo() {
        System.out.println("Nhập mã sản phẩm:");
        int id = scanner.nextInt();
        System.out.println("Nhập tên sản phẩm:");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Nhập giá sản phẩm:");
        long price = scanner.nextLong();
        System.out.println("Nhập mô tả:");
        scanner.nextLine();
        String description = scanner.nextLine();
        Product product = new Product(id, name, price, description);
        return product;
    }
    private void showAllProduct(ProductManagement productManagement) {
        int size = productManagement.size();
        if(size == 0){
            System.out.println("Danh sách trống");
        } else {
            System.out.println("-----Danh sách sản phẩm-----");
            productManagement.displayAll();
        }
    }

    private void menu() {
        System.out.println("1.Hiển thị danh sách sản phẩm");
        System.out.println("2.Thêm sản phẩm mới");
        System.out.println("3.Cập nhật sản phẩm");
        System.out.println("4.Xóa");
        System.out.println("5.Tìm kiếm sản phẩm");
        System.out.println("6.Sắp xếp sản phẩm");
        System.out.println("0.Quay lại");
    }
}
