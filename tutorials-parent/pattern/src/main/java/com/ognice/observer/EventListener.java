package com.ognice.observer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventListener {
    protected Map<Enum, Event> events = new HashMap<>();

    public void addListener(Enum eventType, Object target, Method callback) {
        //注册事件 通过放射调用
        events.put(eventType, new Event(target, callback));
    }

    void trigger(Event e) {
        try {
            e.getCallback().invoke(e.getTaget(), e);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }

    void trigger(Enum call) {
        if (!this.events.containsKey(call)) {
            return;
        }
        trigger(this.events.get(call));

    }

}
