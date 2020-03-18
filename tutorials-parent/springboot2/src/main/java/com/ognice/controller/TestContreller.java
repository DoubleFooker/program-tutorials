package com.ognice.controller;

import com.ognice.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.Map;

/**
 * 测试/测
 */
@Controller
public class TestContreller {

    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @GetMapping(value = "/testUrl")
    @ResponseBody
    public void testDynamicUrl(String path, String method) {
        RequestMappingInfo requestMappingInfo = RequestMappingInfo.paths(path).methods(RequestMethod.GET).build();
        try {
            requestMappingHandlerMapping.registerMapping(requestMappingInfo, "testContreller", TestContreller.class.getDeclaredMethod("test", Map.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return;
    }

    /**
     * test/test
     *
     * @param id
     * @param a
     * @param test
     * @param name
     * @return
     */
    @PostMapping(value = "/person")
    @ResponseBody
    public Person getPerson(@RequestParam Long id, @RequestParam List<String> a, @RequestParam String test, @RequestParam String name) {
        Person person = new Person().setId(id).setName(name);
        return person;
    }

    @PostMapping(value = "/person3")
    @ResponseBody
    public Person getPerson3(@RequestParam Long id, @RequestParam List<String> a, @RequestParam String test, @RequestParam String name) {
        Person person = new Person().setId(id).setName(name);
        return person;
    }

    /**
     * dasdasdasdasdas
     *
     * @param person
     * @return
     */
    @PostMapping(value = "/person2")
    @ResponseBody
    public Person getPerson2(@RequestBody Person person) {
        return person;
    }


    @ResponseBody
    public Map<String, Object> test(Map<String, Object> params) {
        System.out.println(":xxxxxx");
        return params;

    }
}
