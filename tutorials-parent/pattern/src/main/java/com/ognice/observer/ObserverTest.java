package com.ognice.observer;

import java.lang.reflect.Method;

public class ObserverTest {
    public static void main(String[] args) {
        try {    //观察者
            Observer observer = new Observer();
            Method advice = Observer.class.getMethod("advice", Event.class);
            //被观察者
            Subject subject = new Subject();
            subject.addListener(EventType.ON_ADD, observer, advice);
            subject.addListener(EventType.ON_REMOVE, observer, advice);
            subject.add();
            subject.remove();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
