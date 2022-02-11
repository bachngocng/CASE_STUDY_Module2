package com.codegym.controller;

import com.codegym.model.Bill;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BillManagement implements GeneralManagement<Bill>,ReadFile,WriteFile {
    List<Bill> bills = new ArrayList<>();

    public int size(){
        return bills.size();
    }

    //Tìm kiếm theo mã hóa đơn
    public int findBillById(String id) {
        int index = -1;
        for (int i = 0; i < bills.size(); i++) {
            if (bills.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    //Hiển thị
    @Override
    public void displayAll() {
        for (Bill bill : bills) {
            System.out.println(bill);
        }
    }
    //Thêm
    @Override
    public void addNew(Bill bill) {
        bills.add(bill);
    }

    //Sửa
    @Override
    public void updateById(String id, Bill bill) {
        int index = findBillById(id);
        bills.set(index, bill);
    }

    //Xóa
    @Override
    public boolean deleteById(String id) {
        int index = findBillById(id);
        if (index != -1) {
            bills.remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Bill getById(String id) {
        int index = findBillById(id);
        return bills.get(index);
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.bills = (List<Bill>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.bills);
    }
}


