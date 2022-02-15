package com.codegym.view;

import com.codegym.controller.BillManagement;
import com.codegym.model.Bill;
import com.codegym.model.Cart;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class BillMenu implements Serializable {
    public Scanner scanner = new Scanner(System.in);

    public void run() throws IOException, ClassNotFoundException {
        BillManagement billManagement = new BillManagement();
        Cart cart = new Cart();

        int choice = -1;
        File file = new File("bill.txt");
        if (file.exists()) {
            try {
                cart.readFile("yourCart.txt");
                billManagement.readFile("bill.txt");

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showAllBill(billManagement);
                    break;
                case 2:
                    showAddBill(billManagement);
                    break;
                case 3:
                    showUpdateBill(billManagement);
                    break;
                case 4:
                    showDeleteBill(billManagement);
                    break;
                case 5:
                    findBill(billManagement);
                    break;
            }
            try {
                billManagement.writeFile("bill.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (choice != 0);

    }

    private void showAddBill(BillManagement billManagement) throws IOException, ClassNotFoundException {
        System.out.println("Thêm hóa đơn mới");
        scanner.nextLine();
        Bill bill = inputNewBillInfo();
        billManagement.addNew(bill);
    }

    private void showAllBill(BillManagement billManagement) {
        int size = billManagement.size();
        if (size == 0) {
            System.out.println("Danh sách trống");
        } else {
            System.out.println("-----Danh sách hóa đơn-----");
            billManagement.displayAll();
        }
    }

    private void findBill(BillManagement billManagement) {
        int index;
        System.out.println("Tìm kiếm đơn hàng");
        scanner.nextLine();
        System.out.println("Nhập mã đơn hàng muốn tìm");
        int maDonHang1 = scanner.nextInt();
        index = billManagement.findBillById(maDonHang1);
        if (index != -1) {
            System.out.println(billManagement.getById(maDonHang1));
        } else {
            System.out.println("Không tìm thấy");
        }
    }

    private void showDeleteBill(BillManagement billManagement) {
        System.out.println("Xóa đơn hàng");
        scanner.nextLine();
        System.out.println("Nhập mã đơn hàng muốn xóa");
        int maDonHang = scanner.nextInt();
        boolean isDeleted = billManagement.deleteById(maDonHang);
        if (isDeleted) {
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Xóa thất bại vì ko tìm thấy đơn hàng");
        }
    }

    private void showUpdateBill(BillManagement billManagement) throws IOException, ClassNotFoundException {
        System.out.println("Cập nhật hóa đơn");
        scanner.nextLine();
        System.out.println("Nhập mã đơn muốn chỉnh sửa");
        int id = scanner.nextInt();
        int index = billManagement.findBillById(id);
        if (index != -1) {
            Bill oldBill = inputNewBillInfo();
            billManagement.updateById(id, oldBill);
            System.out.println("Cập nhật thành công");
        } else {
            System.out.println("Cập nhật thất bại do không tìm thấy hóa đơn");
        }
    }

    private Bill inputNewBillInfo() throws IOException, ClassNotFoundException {
        System.out.println("Nhập mã hóa đơn");
        String id = scanner.nextLine();
        System.out.println("Nhập tên sản phẩm");
        String name = scanner.nextLine();
        System.out.println("Nhập số lượng");
        int quantity = scanner.nextInt();
        Cart cart = new Cart();
        cart.addNewItem(name);
        long money = cart.totalMoney();
        Bill bill = new Bill(id, cart, quantity, money);
        return bill;
    }

    private void menu() {
        System.out.println("1.Hiển thị danh sách hóa đơn");
        System.out.println("2.Thêm hóa đơn mới");
        System.out.println("3.Sửa hóa đơn");
        System.out.println("4.Xóa hóa đơn");
        System.out.println("5.Tìm kiếm hóa đơn");
        System.out.println("0.Quay lại");
    }
}
