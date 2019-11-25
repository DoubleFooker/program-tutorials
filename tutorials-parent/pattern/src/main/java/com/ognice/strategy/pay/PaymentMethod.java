package com.ognice.strategy.pay;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/12/25
 */
public interface PaymentMethod {
    boolean pay(int cents);
}
