package com.ognice.strategy.spider;

public class TmallProductSpiderStrategy implements ProductSpider {

    @Override
    public WebSiteResult doSpider() {
        Long startTime = System.currentTimeMillis();
        //TODO
        Long endTime = System.currentTimeMillis();
        WebSiteResult webSiteResult =
                new WebSiteResult(new Product("NIKE AJ1", "TM001", "1200", "TMALL"), endTime - startTime);
        return webSiteResult;
    }
}
