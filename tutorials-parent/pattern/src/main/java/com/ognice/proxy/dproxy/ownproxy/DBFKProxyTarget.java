package com.ognice.proxy.dproxy.ownproxy;

public class DBFKProxyTarget implements DBFKProxyTargetInterface {
    @Override
    public void doMethod() {
        System.out.println("执行目标方法");
    }
}
