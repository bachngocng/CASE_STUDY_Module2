package com.codegym.controller;

public interface GeneralManagement <T>{
    void displayAll();

    void addNew(T t);

    void updateById(int id, T t);

    boolean deleteById(int id);

    T getById(int id);
}
