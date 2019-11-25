package com.ognice.strategy.spider;

public class TaobaoProductSpiderStrategy implements ProductSpider {

    @Override
    public WebSiteResult doSpider() {
        Long startTime = System.currentTimeMillis();
        //TODO
        Long endTime = System.currentTimeMillis();
        WebSiteResult webSiteResult =
                new WebSiteResult(new Product("运动鞋", "T001", "200", "TAOBAO"), endTime - startTime);
        return webSiteResult;
    }
}
