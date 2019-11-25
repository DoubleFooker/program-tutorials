package com.ognice.factory.after.simple;

import com.ognice.factory.domain.MengniuMilk;
import com.ognice.factory.domain.Milk;
import com.ognice.factory.domain.TelunsuMilk;

/**
 * 简单工厂
 * 不再需要关心生产过程，只需要关心见过。只需要传入区别参数
 * 缺点：新增类型需要修改源码，扩展不方便
 */
public class SimpleFactory {
    public static Milk getMilk(String milkType) {
        if ("蒙牛".equals(milkType)) {
            return new MengniuMilk();
        } else if ("特仑苏".equals(milkType)) {
            return new TelunsuMilk();
        } else {
            return null;
        }
    }
}
