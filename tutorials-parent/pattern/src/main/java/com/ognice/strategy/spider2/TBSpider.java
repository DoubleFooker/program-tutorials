package com.ognice.strategy.spider2;

import org.springframework.stereotype.Service;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/12/26
 */
@Service
public class TBSpider extends ProductSpider {
    @Override
    public String getWebSite() {
        return "TB";
    }

    @Override
    public String doProductSpider(String url) {
        //System.out.println("TB 爬虫：URL：" + url);
        return url;
    }
}
