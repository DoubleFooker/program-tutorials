package com.ognice.foo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author kaifuhuang
 * @date 2023/2/16
 **/
public class RatedRecord {
    private Long tariffId;
    private Date ratedDay;
    private BigDecimal ratedFee;
    private BigDecimal  ratedFee4;
    private BigDecimal accRatedFee;
    private BigDecimal accReceiveFee;
    private BigDecimal accCent4;
    private BigDecimal lastCent4;
    private String  meta;

    public BigDecimal getLastCent4() {
        return lastCent4;
    }

    public void setLastCent4(BigDecimal lastCent4) {
        this.lastCent4 = lastCent4;
    }

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }

    public Date getRatedDay() {
        return ratedDay;
    }

    public void setRatedDay(Date ratedDay) {
        this.ratedDay = ratedDay;
    }

    public BigDecimal getRatedFee() {
        return ratedFee;
    }

    public void setRatedFee(BigDecimal ratedFee) {
        this.ratedFee = ratedFee;
    }

    public BigDecimal getRatedFee4() {
        return ratedFee4;
    }

    public void setRatedFee4(BigDecimal ratedFee4) {
        this.ratedFee4 = ratedFee4;
    }

    public BigDecimal getAccRatedFee() {
        return accRatedFee;
    }

    public void setAccRatedFee(BigDecimal accRatedFee) {
        this.accRatedFee = accRatedFee;
    }

    public BigDecimal getAccReceiveFee() {
        return accReceiveFee;
    }

    public void setAccReceiveFee(BigDecimal accReceiveFee) {
        this.accReceiveFee = accReceiveFee;
    }

    public BigDecimal getAccCent4() {
        return accCent4;
    }

    public void setAccCent4(BigDecimal accCent4) {
        this.accCent4 = accCent4;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        return "RatedRecord{" +
                "tariffId=" + tariffId +
                ", ratedDay=" + ratedDay +
                ", ratedFee=" + ratedFee +
                ", ratedFee4=" + ratedFee4 +
                ", accRatedFee=" + accRatedFee +
                ", accReceiveFee=" + accReceiveFee +
                ", accCent4=" + accCent4 +
                ", meta='" + meta + '\'' +
                '}';
    }
}
