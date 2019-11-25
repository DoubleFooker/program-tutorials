package com.ognice.proxy.dproxy.cglibproxy;

/**
 * cglib 动态嗲里类 class com.ognice.proxy.dproxy.cglibproxy.CglibProxyTarget$$EnhancerByCGLIB$$d9637b11
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        CglibProxyTarget cglibProxyTarget = (CglibProxyTarget) new CglibProxyObject().getInstance(CglibProxyTarget.class);
        cglibProxyTarget.doMethod();
        System.out.println(cglibProxyTarget.getClass());
    }
}
