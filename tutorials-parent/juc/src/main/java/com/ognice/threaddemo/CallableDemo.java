package com.ognice.threaddemo;

import java.util.concurrent.Callable;

public class CallableDemo implements Callable<String>
{
    @Override
    public String call() throws Exception {
        System.out.println("callable running");
        return "Callable return";
    }
}
