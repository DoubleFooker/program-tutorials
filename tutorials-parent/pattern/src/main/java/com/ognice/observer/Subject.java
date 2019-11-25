package com.ognice.observer;

public class Subject extends EventListener {
    public void add() {
        System.out.println("执行添加方法");
        super.trigger(EventType.ON_ADD);
    }

    public void remove() {
        System.out.println("执行删除方法");
        super.trigger(EventType.ON_REMOVE);

    }
}
