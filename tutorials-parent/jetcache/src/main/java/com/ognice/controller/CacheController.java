package com.ognice.controller;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.ognice.controller.req.UserReq;
import com.ognice.domain.UserInfo;
import com.ognice.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: RestController</p>
 * <p>Description:  </p>
 *
 * @author huangkaifu
 * @date 2019/12/16
 */
@RestController
public class CacheController {
    @Autowired
    CacheService cacheService;

    @GetMapping("/cache")
    @Cached(name = "demoCache", expire = 60, cacheType = CacheType.LOCAL, key = "#name")
    public String testCache(@RequestParam("name") String name) {
        System.out.println("no hit cache controller" + name);
        return "cache val " + name;
    }

    @GetMapping("/cache/list")
    @Cached(name = "demoCache", expire = 60, cacheType = CacheType.LOCAL, key = "#names")
    public String testCache(@RequestParam("names") List<String> names) {
        System.out.println("no hit cache controller" + names);
        return "cache val " + names;
    }

    @PostMapping("/cache/map")
    @Cached(name = "demoCache", expire = 60, cacheType = CacheType.LOCAL, key = "#params")
    public String testCache(@RequestBody Map<String, Object> params) {
        System.out.println("no hit cache controller" + params);
        return "cache val " + params;
    }

    @PostMapping("/cache/obj")
    @Cached(name = "demoCache", expire = 60, cacheType = CacheType.LOCAL, key = "#userReq")
    public String testCache(@RequestBody UserReq userReq) {
        System.out.println("no hit cache controller" + userReq);
        return "cache val " + userReq;
    }

    @GetMapping("/cache/jvm")
    public UserInfo testGetCache() {
        return  cacheService.user();
    }

    @GetMapping("/cache/jvm/modify")
    public UserInfo testModifyCache() {
        UserInfo user = cacheService.user();
        user.setName("dbfk-2");
        return user;
    }
}
