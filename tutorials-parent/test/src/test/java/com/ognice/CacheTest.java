package com.ognice;

import com.ognice.cache.CacheMessage;
import com.ognice.cache.CacheMessageListener;
import com.ognice.cache.CacheType;
import com.ognice.cache.JvmCacheManager;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void cacheTest() throws InterruptedException {
        JvmCacheManager instance = JvmCacheManager.getInstance();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 100000; i++) {
            int finalI = i;
            instance.putRefCache("dbfk", finalI + "", new Random().nextLong());


        }
        // countDownLatch.countDown();
        instance.report();
        for (int i = 0; i < 100000; i++) {
            instance.getRefCache("dbfk", new Random().nextInt(1000) + "");

        }
        TimeUnit.MINUTES.sleep(2);
        instance.report();
        for (int i = 0; i < 100000; i++) {
            Long dbfk = instance.getRefCache("dbfk", new Random().nextInt(1000) + "");
        }
        instance.report();
        while (true) {
        }
    }

    @Test
    public void listCacheTest() {
        JvmCacheManager instance = JvmCacheManager.getInstance();
        List<TestPojo> list = new ArrayList<>();
        list.add(new TestPojo("a"));
        list.add(new TestPojo("b"));
        System.out.println(list);
        instance.putExptsJvm("dbfk", list);
        List dbfk = instance.getExptsCache("dbfk");
        System.out.println(dbfk);
        Assert.assertTrue(list == dbfk);
    }

    @Test
    public void listEvictCacheTest() {
        JvmCacheManager instance = JvmCacheManager.getInstance();
        List<TestPojo> list = new ArrayList<>();
        list.add(new TestPojo("a"));
        list.add(new TestPojo("b"));
        instance.putExptsJvm("dbfk", list);
        List dbfk = instance.getExptsCache("dbfk");
        Assert.assertTrue(list == dbfk);
        instance.removeExptCache("dbfk");
        List cache = instance.getExptsCache("dbfk");
        Assert.assertNull(cache);
    }

    @Test
    public void redisTopic() throws IOException {
        Long start = System.currentTimeMillis();

        List<String> redisKeys = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            JvmCacheManager.getInstance().putRefCache("dbfk", i + "", 1L);
            JvmCacheManager.getInstance().putRefCache("dbfk", i + "-1", 1L);
            redisKeys.add("" + i);

        }
        CacheMessage cacheMessage = new CacheMessage();
        cacheMessage.setAppKey("dbfk")
                .setVersionId(1L)
                .setUserIds(redisKeys);
        redisTemplate.convertAndSend(CacheMessageListener.TOPIC, cacheMessage);
        System.out.println(System.currentTimeMillis() - start);
        System.in.read();
    }

    @Test
    public void removeCache() {
        JvmCacheManager instance = JvmCacheManager.getInstance();
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            instance.removeExptCache("" + i);
        }
        System.out.println(System.currentTimeMillis() - start);
    }


}
