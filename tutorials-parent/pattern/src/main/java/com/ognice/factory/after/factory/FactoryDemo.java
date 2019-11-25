package com.ognice.factory.after.factory;

import com.ognice.factory.domain.Milk;

public class FactoryDemo {
    public static void main(String[] args) {
        Milk milk = new MengniuFactory().getMilk();
    }
}
