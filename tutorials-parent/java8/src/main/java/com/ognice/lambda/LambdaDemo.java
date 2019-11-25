package com.ognice.lambda;

import com.ognice.lambda.function.InterfaceDemo;

/**
 * Created by huangkaifu on 2017/5/12.
 */
public class LambdaDemo {


    public static void main(String[] args) {
        /**
         * 一种
         */
        InterfaceDemo interfaceDemo = (str, str2) -> {
            return str.equals(str2);
        };
        /**
         * 二
         *  * （parameter list）               ->         exprsssion
         */
        InterfaceDemo interfaceDemo1 = (str, str2) -> true;
        interfaceDemo.testFunction("xxx", "xxx");


    }
}
