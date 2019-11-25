package com.ognice.proxy.dproxy.jdkproxy;

/**
 * 动态代理对象
 */
public class JdkProxyTarget implements JdkProxyTargetInterface {
    /**
     * 需要代理方法
     */
    @Override

    public void doMethod(String name) {
        System.out.println("动态代理目标方法");

    }

    public void doMethod() {

    }

    @Override
    public void doMethod2() {
        System.out.println("动态代理目标方法2");

    }
}
