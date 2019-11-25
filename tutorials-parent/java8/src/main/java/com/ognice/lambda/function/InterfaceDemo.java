package com.ognice.lambda.function;

/**
 * 只有一个方法的接口 可使用@functionInterface注解
 * 不加也默认加上
 */
@FunctionalInterface
public interface InterfaceDemo {

    boolean testFunction(String str, String str2);
}
