package com.codegym.view;

import java.util.Scanner;

public class ManagementMenu {
    public static Scanner scanner = new Scanner(System.in);

    public void run() {
        int choice = -1;
        ProductMenu productMenu = new ProductMenu();
        BillMenu billMenu = new BillMenu();
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn:");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    productMenu.run();
                    break;
                }
                case 2: {
                    billMenu.run();
                    break;
                }
            }
        } while (choice != 0);
    }

    private void menu() {
        System.out.println("1. Quản lý sản phẩm");
        System.out.println("2. Quản lý hóa đơn");
        System.out.println("0. Đăng xuất");
    }
}
