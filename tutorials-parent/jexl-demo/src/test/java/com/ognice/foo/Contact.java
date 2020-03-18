package com.ognice.foo;

/**
 * @author kaifuhuang
 * @date 2023/2/16
 **/
public class Contact {

    private int serviceDay;
    private String lastOperationEventType;

    public String getLastOperationEventType() {
        return lastOperationEventType;
    }

    public void setLastOperationEventType(String lastOperationEventType) {
        this.lastOperationEventType = lastOperationEventType;
    }

    public int getServiceDay() {
        return serviceDay;
    }

    public void setServiceDay(int serviceDay) {
        this.serviceDay = serviceDay;
    }
}
