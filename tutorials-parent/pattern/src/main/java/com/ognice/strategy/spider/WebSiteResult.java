package com.ognice.strategy.spider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class WebSiteResult {
    private Product product;
    private Long getTime;
}
