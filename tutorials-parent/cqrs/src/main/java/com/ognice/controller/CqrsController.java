package com.ognice.controller;

import com.ognice.aggregate.UserAccount;
import com.ognice.comand.UserBandCommand;
import com.ognice.comand.UserCreateCommand;
import com.ognice.comand.UserQueryCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Random;

@RestController
@RequestMapping("/cqrs")
public class CqrsController {
    @Autowired
    CommandGateway commandGateway;
    @Autowired
    QueryGateway queryGateway;

    @RequestMapping("/add")
    public void createCommon(@RequestParam("name") String name, @RequestParam("amount") BigDecimal amount, @RequestParam("status") Integer status) {
        commandGateway.sendAndWait(new UserCreateCommand(new Random().nextLong(), name, status, amount));
    }

    @RequestMapping("/band/{id}")
    public void createCommon(@PathVariable("id") Long id) {
        commandGateway.sendAndWait(new UserBandCommand(id, 0));
    }

    @RequestMapping("/query/{id}")
    @ResponseBody
    public UserAccount queryCommon(@PathVariable("id") Long id) {
        return queryGateway.query(new UserQueryCommand(id), UserAccount.class).join();
    }
}
