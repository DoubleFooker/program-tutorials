package com.ognice.service.mock;

import com.ognice.interfaces.DemoService;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/20
 */
public class DemoMockServiceImpl implements DemoService {
    @Override
    public String syaHello(String name) {
        return "sorry i don't know u";
    }

    public static void main(String[] args) {
    }
}
