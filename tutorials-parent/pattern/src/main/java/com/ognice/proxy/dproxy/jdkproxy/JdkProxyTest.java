package com.ognice.proxy.dproxy.jdkproxy;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Jdk动态代理生成class com.sun.proxy.$Proxy0 实例
 */
public class JdkProxyTest {
    public static void main(String[] args) {
        JdkProxyTargetInterface jdkProxyTarget = (JdkProxyTargetInterface) new JdkProxyObjecy().getInstance(new JdkProxyTarget());
        System.out.println(jdkProxyTarget.getClass());
        jdkProxyTarget.doMethod2();
        jdkProxyTarget.doMethod("");

        /**
         * 保存jdk动态生成代码查看
         */
        byte[] batyes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{JdkProxyTargetInterface.class});
        try (FileOutputStream out = new FileOutputStream("/Users/dbfk/Downloads/$Proxy0.class")) {
            out.write(batyes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
