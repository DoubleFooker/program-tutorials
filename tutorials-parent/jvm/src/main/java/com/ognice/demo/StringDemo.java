package com.ognice.demo;

public class StringDemo {
    public void byString() {
        String str = "";
        for (int i = 0; i < 10; i++) {
            str = str + i;
        }
    }

    public void byStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(i);
        }
    }
}
