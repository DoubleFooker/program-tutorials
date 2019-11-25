package com.ognice.strategy.spider2;

import org.springframework.stereotype.Service;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/12/26
 */
@Service
public class JDSpider extends ProductSpider {
    @Override
    public String getWebSite() {
        return "JD";
    }

    @Override
    public String doProductSpider(String url) {
        //System.out.println("JD 爬虫：URL：" + url);
        return url;
    }
}
