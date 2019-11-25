package com.ognice.demo.hashmap;

import java.util.HashMap;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/19
 */
public class TestHashMapLoop {
    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(2, 0.75f);

    //jdk1。8前出现循环连
    public static void main(String[] args) {
        map.put(5, 55);
        new Thread(() -> {
            map.put(7, 77);
            System.out.println(map);
        }, "Thread1").start();
        new Thread(() -> {
            map.put(3, 33);
            System.out.println(map);
        }, "Thread2").start();

    }
}
