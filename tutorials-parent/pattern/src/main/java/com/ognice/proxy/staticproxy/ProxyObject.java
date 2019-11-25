package com.ognice.proxy.staticproxy;

/**
 * 静态代理类
 */
public class ProxyObject {
    private TargetObject targetObject;

    public ProxyObject(TargetObject targetObject) {
        this.targetObject = targetObject;
    }

    public void doMethod() {
        System.out.println("静态代理：执行前");
        targetObject.targetMethod();
        System.out.println("静态代理：执行后");
    }
}
