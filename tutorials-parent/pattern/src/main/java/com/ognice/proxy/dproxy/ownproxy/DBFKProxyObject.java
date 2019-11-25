package com.ognice.proxy.dproxy.ownproxy;

import com.ognice.proxy.dproxy.ownproxy.dbfkproxy.DBFKClassLoader;
import com.ognice.proxy.dproxy.ownproxy.dbfkproxy.DBFKInvocationHandler;
import com.ognice.proxy.dproxy.ownproxy.dbfkproxy.DBFKProxy;

import java.lang.reflect.Method;

public class DBFKProxyObject implements DBFKInvocationHandler {
    private DBFKProxyTargetInterface dbfkProxyTargetInterface;

    public Object getInstance(DBFKProxyTargetInterface dbfkProxyTargetInterface) {
        this.dbfkProxyTargetInterface = dbfkProxyTargetInterface;
        return new DBFKProxy().newProxyInstance(new DBFKClassLoader(), dbfkProxyTargetInterface.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("自定义动态代理执行前");
        method.invoke(this.dbfkProxyTargetInterface, args);
        System.out.println("自定义动态代理执行后");
        return null;
    }
}
