package com.ognice;

import lombok.Data;

import java.util.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        TreeMap<Double, Par> weightMap = new TreeMap<>();
        List<Par> pars = new ArrayList<>();
        pars.add(new Par().setPrio(1).setName("10%"));
        pars.add(new Par().setPrio(1).setName("20%"));
        pars.add(new Par().setPrio(1).setName("30%"));
        pars.add(new Par().setPrio(1).setName("40%"));
        for (Par p : pars) {
            double lastWeight = weightMap.size() == 0 ? 0 : weightMap.lastKey().doubleValue();//统一转为double
            weightMap.put(p.getPrio().doubleValue() + lastWeight, p);//权重累加
        }

        Map<String,Integer> result=new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            double randomWeight = weightMap.lastKey() * Math.random();
            SortedMap<Double, Par> tailMap = weightMap.tailMap(randomWeight, false);
            result.merge(weightMap.get(tailMap.firstKey()).getName(), 1, (prev, one) -> prev + one);

        }
        System.out.println(result);

    }
}
