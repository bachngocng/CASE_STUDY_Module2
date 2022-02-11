package com.codegym.view;

import com.codegym.controller.BillManagement;
import com.codegym.model.Bill;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class BillMenu implements Serializable {
    public Scanner scanner = new Scanner(System.in);

    public void run(){
        BillManagement billManagement = new BillManagement();
        int choice = -1;
        try{
            billManagement.readFile("bill.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn");
            choice = scanner.nextInt();
            switch (choice){
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
            try{
                billManagement.writeFile("bill.txt");
            } catch (IOException e){
                e.printStackTrace();
            }
        } while (choice != 0);

    }

    private void showAddBill(BillManagement billManagement) {
        System.out.println("Thêm hóa đơn mới");
        scanner.nextLine();
        Bill bill = inputNewBillInfo();
        billManagement.addNew(bill);
    }

    private void showAllBill(BillManagement billManagement) {
        int size = billManagement.size();
        if(size == 0){
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
        String maDonHang1 = scanner.nextLine();
        index = billManagement.findBillById(maDonHang1);
        if (index!=-1) {
            System.out.println(billManagement.getById(maDonHang1));
        } else {
            System.out.println("Không tìm thấy");
        }
    }

    private void showDeleteBill(BillManagement billManagement) {
        System.out.println("Xóa đơn hàng");
        scanner.nextLine();
        System.out.println("Nhập mã đơn hàng muốn xóa");
        String maDonHang = scanner.nextLine();
        boolean isDeleted = billManagement.deleteById(maDonHang);
        if (isDeleted){
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Xóa thất bại vì ko tìm thấy đơn hàng");
        }
    }

    private void showUpdateBill(BillManagement billManagement) {
        System.out.println("Cập nhật hóa đơn");
        scanner.nextLine();
        System.out.println("Nhập mã đơn muốn chỉnh sửa");
        String id = scanner.nextLine();
        int index = billManagement.findBillById(id);
        if(index!=-1){
            Bill oldBill = inputNewBillInfo();
            billManagement.updateById(id, oldBill);
            System.out.println("Cập nhật thành công");
        } else {
            System.out.println("Cập nhật thất bại do không tìm thấy hóa đơn");
        }
    }

    private Bill inputNewBillInfo() {
        System.out.println("Nhập mã hóa đơn");
        String id  = scanner.nextLine();
        System.out.println("Nhập tổng giá đơn hàng");
        long pricetag = scanner.nextLong();
        System.out.println("Nhập ngày xuất hóa đơn");
        scanner.nextLine();
        String daySellOut = scanner.nextLine();
        Bill bill = new Bill(id, pricetag, daySellOut);
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
