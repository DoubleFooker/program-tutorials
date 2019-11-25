package com.ognice.testthread.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class ThreadDemo {
    private static final ThreadLocal<SimpleDateFormat> FORMAT_THREAD_LOCAL = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static int balancedStringSplit(String s) {
        int[] nums = new int[]{2, 3, 3, 2, 4};

        int[] sec = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] <= nums[i + 1]) {
            } else {
                if (i == 0) {
                    nums[i] = nums[i + 1];
                } else {
                    nums[i] = nums[i + 1];
                    sec[i + 1] = sec[i];
                }
                break;
            }
        }
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(sec));
        boolean pass = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] <= nums[i + 1]) {
            } else {
                pass = false;
                break;
            }
        }
        boolean pass2 = true;

        for (int i = 0; i < sec.length - 1; i++) {
            if (sec[i] <= sec[i + 1]) {
            } else {
                pass2 = false;
                break;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        System.out.println("sss".indexOf("s", 6));
        System.out.println(balancedStringSplit("LLRRLR"));
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    SimpleDateFormat simpleDateFormat = FORMAT_THREAD_LOCAL.get();
                    Date now = new Date();
                    String format = simpleDateFormat.format(now);
                    System.out.println(Thread.currentThread().getName() + ":before:" + format);
                    // 修改只对当前线程有效
                    FORMAT_THREAD_LOCAL.set(new SimpleDateFormat("yyyy-MM-dd"));
                    System.out.println(Thread.currentThread().getName() + ":after:" + FORMAT_THREAD_LOCAL.get().format(now));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread" + i).start();
        }
        countDownLatch.countDown();
    }
}
