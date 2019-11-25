package com.ognice.cache;


import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;

/**
 * <p>Title: JVMCache</p>
 * <p>Description:  </p>
 *
 * @author huangkaifu
 * @date 2019/11/5
 */
@Slf4j
@Component
public class JvmCacheManager {
    private JvmCacheManager() {
    }

    private volatile static JvmCacheManager INSTANCE;
    private RedisTemplate<Object, Object> redisTemplate;

    public JvmCacheManager(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public static JvmCacheManager getInstance() {
        if (INSTANCE == null) {
            synchronized (JvmCacheManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new JvmCacheManager(SpringUtils.getBean("redisTemplate", RedisTemplate.class));
                }
            }
        }
        return INSTANCE;
    }

    private final Cache relationCache = new CaffeineCache("relationCache", Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES).maximumSize(300000)
            .recordStats().build());

    private Cache exptCache = new CaffeineCache("exptCache", Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES).maximumSize(10000).recordStats().build());
    private Cache varsCache = new CaffeineCache("varsCache", Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES).maximumSize(10000).recordStats().build());

    public Long getRefCache(String appKey, String userId) {
        Long cacheValue = relationCache.get(userId, Long.class);
        if (cacheValue == null) {
            String redisKey = genKey(CacheType.REF, appKey);
            Object redisVal = redisTemplate.opsForHash().get(redisKey, userId);
            if (redisVal != null) {
                log.debug("cache from redis and put to jvm,redisKey:{}", redisKey);
                cacheValue = (Long) redisVal;
                relationCache.put(userId, redisVal);
            }
        }
        return cacheValue;
    }

    public List getExptsCache(String appKey) {
        ArrayList arrayList = exptCache.get(appKey, ArrayList.class);
        return arrayList;
    }

    public List getVarsCache(String userId) {
        ArrayList arrayList = varsCache.get(userId, ArrayList.class);
        return arrayList;
    }


    public  String genKey(CacheType cacheType, String appKey) {
        return cacheType.name() + ":" + appKey;
    }

    public void putRefCache(String appKey, String userId, Long versionId) {
        CacheType ref = CacheType.REF;
        String redisKey = genKey(ref, appKey);
        redisTemplate.opsForHash().put(redisKey, userId, versionId);
        relationCache.put(userId, versionId);
    }


    public void putExptsJvm(String appKey, Object value) {
        exptCache.put(appKey, value);
    }

    public void putVarsJvm(Long versionId, Object vars) {
        varsCache.put(versionId, vars);

    }


    public void clearRefCache( Object... userIds) {
        for (Object obj : userIds) {
            relationCache.evict(obj);
        }
    }


    public void removeExptCache(String appKey) {
        exptCache.evict(appKey);
    }

    public void removeVarsCache(Long versionId) {
        varsCache.evict(versionId);
    }

    public void report() {
        CacheStats stats = ((CaffeineCache) relationCache).getNativeCache().stats();
        System.out.println(stats);
        ((CaffeineCache) exptCache).getNativeCache().stats();
        ((CaffeineCache) varsCache).getNativeCache().stats();
    }

    private void cleanUp() {
        ((CaffeineCache) relationCache).getNativeCache().cleanUp();
        ((CaffeineCache) exptCache).getNativeCache().cleanUp();
        ((CaffeineCache) varsCache).getNativeCache().cleanUp();
    }

}

