package com.ognice.strategy.pay;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/12/25
 */
public abstract class Payment implements PaymentMethod {
    @Override
    public final boolean pay(int cents) {
        System.out.println("使用支付类型：" + getType() + ",支付金额：" + cents);
        return doPay(cents);
    }

    //返回支付类型
    abstract String getType();

    abstract boolean doPay(int cents);
}
