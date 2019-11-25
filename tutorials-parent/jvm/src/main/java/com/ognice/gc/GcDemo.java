package com.ognice.gc;

public class GcDemo {
    public static void main(String[] args) {
        // 设置堆内存20m，年轻代10m，创建7m的对象，使用参数PretenureSizeThreshold(jdk8y已经弃用)设置大对象为5m时直接在old创建
        // -XX:PretenureSizeThreshold只对串行回收器和ParNew有效，对ParallGC无效
        // -verbose:gc -XX:+PrintGCDetails -Xmx20m -Xms20m -Xmn10m -XX:PretenureSizeThreshold=5m
        byte[] bytes = new byte[5 * 1024 * 1024];
    }

    public void byString(){
        String str="";
        for (int i=0;i<10;i++){
            str+=i;
        }
    }
    public void byStringBuilder(){
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<10;i++){
            stringBuilder.append(i);
        }
    }
}
