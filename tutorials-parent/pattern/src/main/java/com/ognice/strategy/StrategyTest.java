package com.ognice.strategy;

import com.ognice.strategy.spider.WebSiteType;

/**
 * 策略模式， 将一些固定的算法封装，用户端只需要选择去调用对应的算法获得结果。
 */
public class StrategyTest {
    public static void main(String[] args) {
        DBFKSpider dbfkSpider = new DBFKSpider();
        System.out.println(dbfkSpider.doSpider(WebSiteType.JD));
    }
}
