package com.ognice.strategy.spider2;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/12/26
 */
public abstract class ProductSpider implements ISpider {
    public static Map<String, ProductSpider> spiderMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void init() {
        spiderMap.put(getWebSite(), this);
    }

    @Override
    public final String doSpider(String url) {
        //System.out.println(getWebSite());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return doProductSpider(url);
    }

    public abstract String getWebSite();

    public abstract String doProductSpider(String url);
}
