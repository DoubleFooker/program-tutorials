package com.ognice.service;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.ognice.domain.UserInfo;
import org.springframework.stereotype.Component;

/**
 * <p>Title: CacheService</p>
 * <p>Description:  </p>
 *
 * @author huangkaifu
 * @date 2019/12/30
 */
@Component
public class CacheService {
    @Cached(name = "demo", expire = 60, cacheType = CacheType.LOCAL)
    public String demo() {
        return "demoCache";
    }

    @Cached(name = "demo", expire = 60, cacheType = CacheType.LOCAL)
    public UserInfo user() {

        return new UserInfo().setAge(11).setName("dbfk");
    }
}
