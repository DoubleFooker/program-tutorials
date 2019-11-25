package com.ognice.demo.processordemo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/16
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@RequiredArgsConstructor
public class Request {
    private String name;
}
