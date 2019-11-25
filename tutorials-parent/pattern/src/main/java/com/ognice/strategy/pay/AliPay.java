package com.ognice.strategy.pay;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/12/25
 */
public class AliPay extends Payment {
    @Override
    String getType() {
        return "ali";
    }

    @Override
    boolean doPay(int cents) {
        System.out.println("alipay:" + cents);

        return true;
    }
}
