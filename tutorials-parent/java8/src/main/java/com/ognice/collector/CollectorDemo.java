package com.ognice.collector;

import com.ognice.lambda.demo.Apple;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class CollectorDemo {

    private static void testAvagingLong(List list) {
        /**
         * AVERAGINGDOUBLE
         * 求平均数
         */
        Optional.ofNullable(list.stream().collect(Collectors.averagingLong(Apple::getWeight))).ifPresent(System.out::println);
    }

    private static void testAvagingInt(List list) {
        Optional.ofNullable(list.stream().collect(Collectors.averagingInt(Apple::getWeight))).ifPresent(System.out::println);

    }

    private static void testAvagingDouble(List list) {
        Optional.ofNullable(list.stream().collect(Collectors.averagingDouble(Apple::getWeight))).ifPresent(System.out::println);

    }

    /**
     * collectingAndThen
     *
     * @param args
     */
    private static void testCollectingAndThen(List<Apple> list) {
        List<Apple> collect = list.stream()
                .filter(a -> a.getWeight() > 100)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));


    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple(100, "color1")
                , new Apple(200, "color1")
                , new Apple(300, "red")
                , new Apple(400, "red")
                , new Apple(500, "green"));


        /**
         * collectingAndThen
         */

    }
}
