package com.ognice.proxy.dproxy.ownproxy.dbfkproxy;

import java.lang.reflect.Method;

public interface DBFKInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
