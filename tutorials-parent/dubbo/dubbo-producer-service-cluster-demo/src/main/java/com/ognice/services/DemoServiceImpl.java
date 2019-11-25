package com.ognice.services;

import com.alibaba.dubbo.config.annotation.Service;
import com.ognice.interfaces.DemoService;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/20
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String syaHello(String name) {
        System.out.println("调用服务2");

        return "hello 服务2" + name;
    }
}
