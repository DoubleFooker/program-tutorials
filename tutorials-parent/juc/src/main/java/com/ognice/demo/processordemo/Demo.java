package com.ognice.demo.processordemo;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/16
 */
public class Demo {
    public static void main(String[] args) {
        Request request = new Request();
        request.setName("ceshi");
        SaveProcessor saveProcessor = new SaveProcessor();
        saveProcessor.start();
        PrintProcessor printProcessor = new PrintProcessor(saveProcessor);
        printProcessor.start();
        printProcessor.doRequestProcessor(request);

    }
}
