package com.ognice.testthread.utils;

import java.math.BigDecimal;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/9/10
 */
public class DoubleTest {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(1.001);
        BigDecimal bigDecimal1 = new BigDecimal(1.000);
        System.out.println(bigDecimal.compareTo(bigDecimal1));
    }
}
