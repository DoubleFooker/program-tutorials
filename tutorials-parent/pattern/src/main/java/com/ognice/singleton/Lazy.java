package com.ognice.singleton;

public class Lazy {
    private Lazy() {
    }

    private static Lazy lazy = null;

    /**
     * 存在多实例情况
     *
     * @return
     */

    public static Lazy getInstance() {
        if (lazy == null) {
            lazy = new Lazy();
        }
        return lazy;
    }

    /**
     * 加锁,耗时大
     */
    public static synchronized Lazy getInstanceSyc() {
        if (lazy == null) {
            lazy = new Lazy();
        }
        return lazy;
    }

    /**
     * 内部类实现
     */

    private static final class LazyHolder {
        private static final Lazy lazy = new Lazy();
    }

    public static synchronized Lazy getInstanceByLazyHolder() {
        return LazyHolder.lazy;
    }

}
