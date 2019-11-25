package com.ognice.juc;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/15
 */
public class Test implements Serializable {
    public static AtomicInteger count = new AtomicInteger(0);
    public static int clientTotal = 5000;

    public static void main(String[] args) throws Exception {
        char[] chars = (12 + "").toCharArray();
        for(Character c:chars){
            System.out.println(c);
        }

List<Character> a=new ArrayList<>();
        a.toArray();
        String aa="xxx.bb";
        System.out.println(aa.replaceAll("\\.",""));

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < clientTotal; i++) {
//            executorService.execute(() -> {
//                try {
//                    add();
//                } catch (Exception e) {
//                    //.error("exception", e);
//                }
//            });
//        }
//        executorService.shutdown();
//        System.out.println("****************" +
        //reverse(1123333349);

        System.out.println(Arrays.toString(demo(new int[]{2,5})));
    }

    public static String[] demo(int[] nums) {


        String[] r = new String[nums.length];
        List<Integer> tmp = new ArrayList<>();
        Map<Integer, Integer> indexs = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            tmp.add(nums[i]);
            indexs.put(nums[i], i);
        }
        tmp.sort(Comparator.reverseOrder());
        if (nums.length == 1) {
            r[indexs.get(tmp.get(0))] = "Gold Medal";
        }
        if (nums.length == 2) {
            r[indexs.get(tmp.get(0))] = "Gold Medal";
            r[indexs.get(tmp.get(1))] = "Silver Medal";
        }
        if (nums.length >= 3) {
            r[indexs.get(tmp.get(0))] = "Gold Medal";
            r[indexs.get(tmp.get(1))] = "Silver Medal";
            r[indexs.get(tmp.get(2))] = "Bronze Medal";
            for (int i = 3; i < tmp.size(); i++) {
                r[indexs.get(tmp.get(i))] = i + 1 + "";
            }
        }
        Executors.newWorkStealingPool();

        return r;
    }

    public static boolean test(int num) {
        if (num % 2 != 0) {
            return false;
        }
        int result = 1;
        int mid = num / 2;
        for (int i = 2; i < mid; i++) {
            if (num % i == 0) {
                result += i + num / i;
            }
            if (result > num) {
                return false;
            }
        }
        return result == num;
    }

    private static void add() {
        System.out.println(count.get() + "=======" + count.incrementAndGet());
    }

    public static void reverse(int x) {
        int hight = 1;
        int tmp = x;
        while (tmp / 10 != 0) {
            hight++;
            tmp = tmp / 10;
        }
        int[] indexNum = new int[hight];
        for (int i = hight; i > 0; i--) {
            indexNum[i - 1] = x % 10;
            x = x / 10;
        }
        System.out.println(Arrays.toString(indexNum));
        int[] reverse = new int[hight];
        for (int i = 0, j = indexNum.length; i < indexNum.length; i++) {
            reverse[--j] = indexNum[i];
        }
        System.out.println(Arrays.toString(reverse));

    }
}
