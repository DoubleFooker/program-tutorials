package com.ognice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.ognice.interfaces.DemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hello world!
 */
@SpringBootApplication
@DubboComponentScan(basePackages = "com.ognice")
@Controller("/test")
public class App {
    @Reference(cluster = "failfast", mock = "com.ognice.service.mock.DemoMockServiceImpl", timeout = 1000)
    DemoService demoService;

    @RequestMapping("/dubbo")
    @ResponseBody
    public String test() {
        return demoService.syaHello("dbfk");
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(App.class);

        //System.out.println(run.getBean("com.ognice.interfaces.DemoService"));
    }
}
