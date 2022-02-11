package com.codegym.model;

import java.io.Serializable;

public class Bill implements Serializable {
    private String id;
    private long totalMoney;
    private String daySellOut;
    private final double VATfee = 0.1;

    public Bill() {
    }

    public Bill(String id, long totalMoney, String daySellOut) {
        this.id = id;
        this.totalMoney = totalMoney;
        this.daySellOut = daySellOut;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getDaySellOut() {
        return daySellOut;
    }

    public void setDaySellOut(String daySellOut) {
        this.daySellOut = daySellOut;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id='" + id + '\'' +
                ", pricetag=" + totalMoney +
                ", daySellOut='" + daySellOut + '\'' +
                '}';
    }
}
