package com.ognice.stream;

import com.ognice.stream.entity.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class StreamDemo {
    /**
     * 获取符合条件的字段结果集
     *
     * @return
     */
    private static List<String> getMenuByStream(List<Dish> dishes) {
        return dishes.stream().filter(d -> d.getKaloli() < 2).map(Dish::getName).collect(toList());

    }

    public static void main(String[] args) {
        List<Dish> dishList = Arrays.asList(new Dish("dish1", 1, 1), new Dish("dish2", 2, 2));
        List<String> menuByStream = getMenuByStream(dishList);
        System.out.println(menuByStream);

    }
}
