package com.ognice.testthread.utils;

public class CommentUtil {
    public static String setString() {
        StringBuilder sb = new StringBuilder("测试多线程自负空值");
        sb.append("-a");
        sb.append("-b");
        sb.append("-c");
        String val = sb.toString();
        System.out.println(Thread.currentThread().getName() + "值**" + val + "++");
        return val;
    }
}
