package com.ognice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * some desc
 *
 * @author dbfk
 * @date 2020/3/11
 **/
@Component
public class DemoService {
    @Autowired
    RedisTemplate redisTemplate;
    public void serKey(){
        redisTemplate.opsForValue().set("test5","test");
    }
}
