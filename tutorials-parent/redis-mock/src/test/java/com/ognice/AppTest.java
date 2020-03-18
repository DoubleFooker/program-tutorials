package com.ognice;

import static org.junit.Assert.assertTrue;

import com.ognice.service.DemoService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import redis.embedded.RedisServer;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    private static RedisServer redisServer;
    @Autowired
    DemoService demoService;
    @Autowired
    RedisTemplate redisTemplate;

    @BeforeClass
    public static void init() {
        try {

            redisServer = RedisServer.builder()
                    .port(6110)
                    // windows 需要设置最大内存，启动才不报错
                    .setting("maxmemory 128M")
                    .build();
            redisServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static  void destory() {

        redisServer.stop();
    }

    @Test
    public void shouldAnswerWithTrue() {
        System.out.println(redisServer.isActive());
       demoService.serKey();
        Object test = redisTemplate.opsForValue().get("test4");
        Assert.assertTrue("test".equals(test.toString()));
    }
}
