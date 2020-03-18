package com.ognice.foo;

import java.math.BigDecimal;

/**
 * @author kaifuhuang
 * @date 2023/2/16
 **/
public class Pricing {

    private BigDecimal feeRate;
    private BigDecimal fixedFee;

    private String endRefValue;

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public BigDecimal getFixedFee() {
        return fixedFee;
    }

    public void setFixedFee(BigDecimal fixedFee) {
        this.fixedFee = fixedFee;
    }

    public String getEndRefValue() {
        return endRefValue;
    }

    public void setEndRefValue(String endRefValue) {
        this.endRefValue = endRefValue;
    }
}
