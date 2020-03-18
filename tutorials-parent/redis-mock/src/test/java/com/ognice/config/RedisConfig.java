package com.ognice.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.redis.support.collections.RedisProperties;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * some desc
 *
 * @author dbfk
 * @date 2020/3/11
 **/

@TestConfiguration
public class RedisConfig {

    private RedisServer redisServer;

    public RedisConfig(RedisProperties redisProperties) {
        // this.redisServer = RedisServer.newRedisServer();
        this.redisServer = RedisServer.builder().port(6110).setting("maxmem 128M").build();
    }

    @PostConstruct
    public void postConstruct() {
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
}
