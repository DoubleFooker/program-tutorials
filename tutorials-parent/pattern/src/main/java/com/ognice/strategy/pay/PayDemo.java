package com.ognice.strategy.pay;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/12/25
 */
public class PayDemo {
    public static void main(String[] args) {
        PaymentFactory.getPayment("card").pay(100);
        PaymentFactory.getPayment("ali").pay(100);
    }
}
