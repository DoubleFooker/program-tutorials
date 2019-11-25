package com.ognice.factory.after.simple;

import com.ognice.factory.domain.Milk;

public class SimpleFactoryDemo {
    public static void main(String[] args) {

        Milk milk = SimpleFactory.getMilk("特仑苏");
        System.out.println(milk.getName());
    }
}
