package com.ognice.services;

import com.ognice.domain.User;
import reactor.core.publisher.Mono;

/**
 * Created by huangkaifu on 2017/5/12.
 */
public interface UserService {
    Mono<User> getUser(Integer id);
}
