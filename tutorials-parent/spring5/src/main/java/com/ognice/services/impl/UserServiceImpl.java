package com.ognice.services.impl;

import com.ognice.domain.User;
import com.ognice.services.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Created by huangkaifu on 2017/5/12.
 */
@Service
public class UserServiceImpl implements UserService {
    public Mono<User> getUser(Integer id){
        return Mono.just(new User(id.toString(),"测试用户数据"));
    }
}
