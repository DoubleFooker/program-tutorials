package com.ognice.strategy.spider;

public class JDProductSpiderStrategy implements ProductSpider {
    @Override
    public WebSiteResult doSpider() {
        Long startTime = System.currentTimeMillis();
        //TODO
        Long endTime = System.currentTimeMillis();
        WebSiteResult webSiteResult =
                new WebSiteResult(new Product("JD", "001", "10.0", "京东"), endTime - startTime);
        return webSiteResult;
    }
}
