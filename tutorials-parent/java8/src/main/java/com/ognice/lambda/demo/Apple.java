package com.ognice.lambda.demo;

public class Apple {
    private int weight;
    private String color;

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public Apple setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Apple setColor(String color) {
        this.color = color;
        return this;
    }

    @Override
    public String toString() {
        return "weight:" + this.weight + ",color:" + this.color;
    }
}
