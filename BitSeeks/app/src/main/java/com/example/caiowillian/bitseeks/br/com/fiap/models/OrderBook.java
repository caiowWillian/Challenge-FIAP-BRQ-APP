package com.example.caiowillian.bitseeks.br.com.fiap.models;

/**
 * Created by Caio Willian on 17/10/2017.
 */

public class OrderBook {
    private String company;
    private double value;

    public OrderBook(String company, double value) {
        this.company = company;
        this.value = value;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
