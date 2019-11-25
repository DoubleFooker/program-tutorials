package com.ognice.proxy.dproxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 * 需要实现 InvocationHandler 接口
 * 所有方法都执行了动态方法
 */
public class JdkProxyObjecy implements InvocationHandler {
    /**
     * 目标对象,必须实现接口，不然 Proxy.newProxyInstance 动态生成的对象会报类型转换错误。
     */
    private JdkProxyTargetInterface jdkProxyTarget;

    public Object getInstance(JdkProxyTargetInterface jdkProxyTarget) {
        this.jdkProxyTarget = jdkProxyTarget;
        Class<?> clazz = jdkProxyTarget.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理，代理执行前：");
        method.invoke(this.jdkProxyTarget, args);
        System.out.println("动态代理，代理执行后：");
        return null;
    }
}
