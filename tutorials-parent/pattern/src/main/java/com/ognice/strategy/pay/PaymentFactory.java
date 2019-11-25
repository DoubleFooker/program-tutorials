package com.ognice.strategy.pay;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/12/25
 */
public class PaymentFactory {
    public static Payment getPayment(String payType) {
        switch (payType) {
            case "ali":
                return new AliPay();
            case "card":
                return new CardPay();
            default:
                throw new RuntimeException("payType err");
        }
    }
}
