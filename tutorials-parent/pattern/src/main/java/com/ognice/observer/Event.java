package com.ognice.observer;

import lombok.*;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@RequiredArgsConstructor
public class Event {
    //源头
    private Object src;
    //目标
    @NonNull
    private Object taget;
    //回调
    @NonNull
    private Method callback;
    //触发
    private String trigger;
}
