package com.ognice.factory.after.abfactory;

import com.ognice.factory.domain.Milk;

/**
 * 抽象工厂
 * 可以定制公用的方法
 * 用户只有选择的权利，避免传入参数不存在，等错误的出现
 * 易于扩展。新增一个类型时，只需在抽象中添加一个方法，修改工厂即可。
 */
public abstract class AbstractFactory {
    public abstract Milk getMengniuMilk();

    public abstract Milk getTelunsuMilk();

}
