package com.ognice.lambda.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StreamListDemo {
    public static void main(String[] args) {


        List<String> list = Arrays.asList("a", "b");
        List<String> list2 = Collections.EMPTY_LIST;
        list.stream().forEach(str -> {
            str.trim();
            list2.add(str);
        });
    }
}
