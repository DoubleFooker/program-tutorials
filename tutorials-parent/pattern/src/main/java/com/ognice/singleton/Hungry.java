package com.ognice.singleton;

/**
 * 饿汉式
 * java加载顺序
 * 先静态、后动态
 * 先属性、后方法
 * 先上后下
 */
public class Hungry {
    private Hungry() {
    }

    private static final Hungry hungry = new Hungry();

    public static Hungry getInstance() {
        System.out.println(System.currentTimeMillis() + "-->" + hungry);
        return hungry;
    }
    

}
