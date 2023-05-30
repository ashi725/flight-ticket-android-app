package com.example.myapplication.util;

import java.util.List;

public class Ticket {

    private List<Integer> date;
    private double price;
    private Country destination;

    // Constructor
    public Ticket(List<Integer> date, double price, Country destination){
        this.date = date;
        this.price = price;
        this.destination = destination;
    }

    public List<Integer> getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public Country getDestination() {
        return destination;
    }
}
