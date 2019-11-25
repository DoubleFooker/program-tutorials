package com.ognice.controller;

import com.ognice.config.DynamicRouteServiceImpl;
import com.ognice.config.RedisRes;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PathRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.Predicate;

@RestController
public class RouteController {
    @Autowired
    RedisRes redisRes;
    @Autowired
    DynamicRouteServiceImpl dynamicRouteService;

    @RequestMapping("/save")
    public void add() throws URISyntaxException {
        RouteDefinition data = new RouteDefinition();
        data.setId(RandomUtils.nextInt() + "route");
        data.setUri(new URI("http://127.0.0.1:39090"));
        data.setOrder(RandomUtils.nextInt());
        List<PredicateDefinition> pre = new ArrayList<>();
        PredicateDefinition p = new PredicateDefinition();
        p.setName("Path");
        Map args = new HashMap();
        args.put("key", "/demo/test22");
        p.setArgs(args);
        pre.add(p);
        data.setPredicates(pre);

        FilterDefinition filterDefinition=new FilterDefinition();
        filterDefinition.setName("RequestRateLimiter");
        Map args2 = new HashMap();
        args2.put("redis-rate-limiter.replenishRate", "1");
        args2.put("redis-rate-limiter.burstCapacity", "1");
        filterDefinition.setArgs(args2);
        List filters=new ArrayList();
        filters.add(filterDefinition);
        data.setFilters(filters);
        Mono<RouteDefinition> definitionMono = Mono.just(data);
        redisRes.save(definitionMono);
        dynamicRouteService.refresh();

    }
    @RequestMapping("/demo/test2")
    public String demo(){
        return "xxx";
    }
}
