package com.ognice.strategy.pay;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/12/25
 */
public class CardPay extends Payment {
    @Override
    String getType() {
        return "card";
    }

    @Override
    boolean doPay(int cents) {
        System.out.println("cardpay:" + cents);
        return true;
    }
}
