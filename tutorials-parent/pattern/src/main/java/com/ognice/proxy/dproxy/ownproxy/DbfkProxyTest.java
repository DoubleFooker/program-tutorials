package com.ognice.proxy.dproxy.ownproxy;

/**
 * 自己实现动态代理
 */
public class DbfkProxyTest {


    public static void main(String[] args) {
        DBFKProxyTargetInterface dbfkProxyTargetInterface =
                (DBFKProxyTargetInterface) new DBFKProxyObject().getInstance(new DBFKProxyTarget());
        dbfkProxyTargetInterface.doMethod();
       

    }
}
