package com.ognice.threaddemo;

public class ThreadExtendDemo extends Thread{

    @Override
    public void run() {
        System.out.println("Extend Thread running");
    }
}