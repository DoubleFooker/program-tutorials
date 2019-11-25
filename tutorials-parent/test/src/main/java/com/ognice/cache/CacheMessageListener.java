package com.ognice.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>Title: CacheMessageListener</p>
 * <p>Description:  </p>
 *
 * @author huangkaifu
 * @date 2019/11/8
 */
@Slf4j
@Component
public class CacheMessageListener implements MessageListener {
    public static final String TOPIC = "cache:redis:caffeine:ab:topic";
    private RedisTemplate<String, Object> redisTemplate;


    public CacheMessageListener(RedisTemplate<String, Object> redisTemplate) {
        super();
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        Long start = System.currentTimeMillis();
        CacheMessage cacheMessage = (CacheMessage) redisTemplate.getValueSerializer().deserialize(message.getBody());
        if (cacheMessage == null) {
            return;
        }
        try {
            String appKey = cacheMessage.getAppKey();
            JvmCacheManager instance = JvmCacheManager.getInstance();
            CacheType ref = CacheType.REF;
            Object[] userIds = cacheMessage.getUserIds().toArray();
            String redisKey = instance.genKey(ref, appKey);
            redisTemplate.opsForHash().delete(redisKey, userIds);
            instance.clearRefCache(userIds);
            instance.removeExptCache(appKey);
            instance.removeVarsCache(cacheMessage.getVersionId());
            System.out.println("re:" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            log.error("topic cacheMessage err!", e);
        }
        // log.debug("recevice a redis topic message, clear local cache, the cacheType is {}, the appKey is {},keys:{}", cacheMessage.getCacheType(), cacheMessage.getKey(), cacheMessage.getRedisKeys());

    }

}