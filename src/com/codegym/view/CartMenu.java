package com.codegym.view;

import com.codegym.controller.BillManagement;
import com.codegym.controller.ProductManagement;
import com.codegym.model.Cart;

import java.io.*;
import java.util.Scanner;

public class CartMenu implements Serializable {
    public static Scanner scanner = new Scanner(System.in);

    public void run() throws IOException, ClassNotFoundException {
        ProductManagement productManagement = new ProductManagement();
        productManagement.readFile("product.txt");
        Cart cart = new Cart();
        BillManagement billManagement = new BillManagement();
        int choice = -1;
        try {
            cart.readFile("yourCart.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        do {
            System.out.println("1.Xem giỏ hàng của bạn");
            System.out.println("2.Xem gian hàng");
            System.out.println("3.Mua hàng");
            System.out.println("4.Xóa hàng");
            System.out.println("5.Tính tiền");
            System.out.println("0.Quay lại");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showCart(cart);
                    break;
                case 2:
                    showProductMenu(productManagement);
                    break;
                case 3:
                    addProductToCart(cart);
                    break;
                case 4:
                    removeProductFromCart(cart);
                case 5:
                    System.out.println("--Tính tiền--");
                    System.out.println("Tổng giá trị giỏ hàng của bạn: " + cart.totalMoney());
                    cart.removeAllProduct();
                    try {
                        billManagement.readFile("bill.txt");
                    } catch (EOFException e){
                        e.printStackTrace();
                    }
                    billManagement.writeFile("bill.txt");
            }
            try {

                cart.writeFile("yourCart.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (choice != 0);
    }

    private void removeProductFromCart(Cart cart) {
        System.out.println("---Xóa hàng---");
        System.out.println("nhập tên sản phẩm bạn muốn xóa khỏi giỏ");
        scanner.nextLine();
        String name = scanner.nextLine();
        cart.removeItem(name);
    }

    private void addProductToCart(Cart cart) {
        System.out.println("---Mua hàng---");
        System.out.println("Nhập tên sản phẩm bạn muốn mua");
        scanner.nextLine();
        String name = scanner.nextLine();
        cart.addNewItem(name);
        System.out.println("Đã thêm sản phẩm vào giỏ hàng!!!");
    }

    private void showProductMenu(ProductManagement productManagement) {
        System.out.println("---Danh sách sản phẩm---");
        productManagement.displayAll();
    }

    private void showCart(Cart cart) {
        System.out.println("---Giỏ Hàng---");
        int size = cart.size();
        if (size == 0) {
            System.out.println("Giỏ không có sản phẩm nào");
        } else {
            System.out.println("---Danh sách đồ đã thêm vào giỏ hàng---");
            cart.displayCart();
        }
    }

}
