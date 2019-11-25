package com.ognice.strategy;

import com.ognice.strategy.spider.WebSiteResult;
import com.ognice.strategy.spider.WebSiteType;

public class DBFKSpider {
    public WebSiteResult doSpider(WebSiteType webSiteType) {
        return webSiteType.get().doSpider();
    }

}
