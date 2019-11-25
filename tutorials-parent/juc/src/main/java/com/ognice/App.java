package com.ognice;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    private static int x = 0;
    public static volatile boolean isfinish = false;

    public void write() {
        x = 1; // 1
        isfinish = true; //2
    }

    public void read() {
        if (!isfinish) { // 3
            int a = x; // 4
        }

    }

    public static void main(String[] args) {
        Map a=new HashMap();

    }


}
