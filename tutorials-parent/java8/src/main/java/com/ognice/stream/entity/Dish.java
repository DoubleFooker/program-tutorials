package com.ognice.stream.entity;

public class Dish {
    @Override
    public String toString() {

        return this.name;
    }

    private String name;
    private double price;
    private double kaloli;

    public Dish(String name, double price, double kaloli) {
        this.name = name;
        this.price = price;
        this.kaloli = kaloli;
    }

    public String getName() {
        return name;
    }

    public Dish setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Dish setPrice(double price) {
        this.price = price;
        return this;
    }

    public double getKaloli() {
        return kaloli;
    }

    public Dish setKaloli(double kaloli) {
        this.kaloli = kaloli;
        return this;
    }
}
