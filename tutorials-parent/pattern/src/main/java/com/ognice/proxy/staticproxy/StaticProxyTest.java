package com.ognice.proxy.staticproxy;

public class StaticProxyTest {
    public static void main(String[] args) {
        TargetObject targetObject = new TargetObject();
        ProxyObject proxyObject = new ProxyObject(targetObject);
        proxyObject.doMethod();
    }
}
