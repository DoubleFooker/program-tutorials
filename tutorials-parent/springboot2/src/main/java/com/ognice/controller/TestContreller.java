package com.ognice.controller;

import com.ognice.entity.Person;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestContreller {
    @PostMapping(value = "/person", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public Person getPerson(@RequestParam Long id, @RequestParam String name) {
        Person person = new Person().setId(id).setName(name);
        return person;
    }
}
