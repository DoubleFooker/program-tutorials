package com.ognice.strategy.spider;

/**
 * 爬虫目标枚举
 */
public enum WebSiteType {
    JD(new JDProductSpiderStrategy()),
    TAOBAO(new TaobaoProductSpiderStrategy()),
    TMALL(new TmallProductSpiderStrategy());
    private ProductSpider productSpider;

    WebSiteType(ProductSpider productSpider) {
        this.productSpider = productSpider;
    }

    public ProductSpider get() {
        return productSpider;
    }
}
