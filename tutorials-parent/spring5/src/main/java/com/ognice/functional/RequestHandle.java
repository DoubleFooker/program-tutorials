package com.ognice.functional;


import com.ognice.domain.User;
import com.ognice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.reactive.function.BodyExtractors.toDataBuffers;
import static org.springframework.web.reactive.function.BodyExtractors.toFormData;
import static org.springframework.web.reactive.function.BodyInserters.*;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * Created by huangkaifu on 2017/5/12.
 */

/**
 * 需要学习内容 mono  flux
 */
public class RequestHandle {
    //登陆例子
    public Mono<ServerResponse> handleLogin(ServerRequest request) {
        return request
                .body(toFormData())
                .map(MultiValueMap::toSingleValueMap)//请求类型不同获取会异常
                .map(formData -> {
                    System.out.println("请求数据: " + formData.toString());
                    //账号密码判断
                    if ("user".equals(formData.get("user")) && "psw".equals(formData.get("token"))) {
                        return ok()
                                .body(Mono.just("欢迎登陆!"), String.class)
                                .block();
                    }
                    return ServerResponse
                            .badRequest()
                            .build()
                            .block();
                });
    }

    @Autowired
    UserService userService;

    public Mono<ServerResponse> getOne(ServerRequest request) {
        Mono<User> user = Mono.justOrEmpty(request.pathVariable("id"))
                .map(Integer::valueOf)
                .then(Mono.just(new User("id","测试用户数据")));
        return ok().body(fromPublisher(user, User.class));
    }

    //上传例子
    public Mono<ServerResponse> handleUpload(ServerRequest request) {
        return request
                .body(toDataBuffers())
                .collectList()
                .map(dataBuffers -> {
                    AtomicLong atomicLong = new AtomicLong(0);
                    dataBuffers.forEach(d -> atomicLong.addAndGet(d
                            .asByteBuffer()
                            .array().length));
                    System.out.println("数据长度:" + atomicLong.get());
                    return ok()
                            .body(fromObject(atomicLong.toString()))
                            .block();
                });
    }

}

