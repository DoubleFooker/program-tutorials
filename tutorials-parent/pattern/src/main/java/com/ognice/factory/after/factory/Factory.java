package com.ognice.factory.after.factory;

import com.ognice.factory.domain.Milk;

/**
 * 工厂特有化，各自负责自己的生产
 * 缺点 需要选择工厂
 */
public interface Factory {
    Milk getMilk();
}
