package com.ognice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/25
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@RequiredArgsConstructor
public class Product {
    private long id;
    private String name;
    private int sellcount;
    private double price;
}
