package com.ognice.factory.after.abfactory;

import com.ognice.factory.domain.Milk;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        MilkFactory milkFactory = new MilkFactory();
        Milk milk = milkFactory.getMengniuMilk();
        System.out.println(milk.getName());
    }
}
