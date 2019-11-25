package com.ognice;

import com.ognice.domain.User;
import com.ognice.functional.RequestHandle;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.server.WebServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.WebHandler;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;
/**

 * Created by huangkaifu on 2017/5/12.
 */
public class FunctionalWebServer {
    private static final User BRAD_PITT = new User("用户1", "密码1");
    private static final User TOM_HANKS = new User("用户2", "密码2");
    private static final List<User> users = new CopyOnWriteArrayList<>(Arrays.asList(BRAD_PITT, TOM_HANKS));

    //配置路由
    private RouterFunction<ServerResponse> routingFunction() {
        RequestHandle requestHandle = new RequestHandle();

        RouterFunction<ServerResponse> restfulRouter = route(GET("/"), serverRequest -> ok().body(Flux.fromIterable(users), User.class)).andRoute(POST("/"), serverRequest -> serverRequest
                .bodyToMono(User.class)
                .doOnNext(users::add)
                .then(ok().build()));

        RouterFunction<ServerResponse> testRouter = route(GET("/testroute"), serverRequest -> ok().body(fromObject("testroute")));

        return route(GET("/test"), serverRequest -> ok().body(fromObject("helloworld")))
                .and(restfulRouter)
                .andRoute(POST("/login"), requestHandle::handleLogin)
                .andRoute(GET("/getone/{id}"), requestHandle::getOne)
                .andRoute(POST("/upload"), requestHandle::handleUpload)
                .and(RouterFunctions.resources("/files/**", new ClassPathResource("files/")))
                .andNest(path("/actor"), restfulRouter)
                .filter((request, next) -> {
                    System.out.println("请求拦截: " + request.path());
                    return next.handle(request);
                });
    }


    //启动服务
    WebServer start() throws Exception {
        WebHandler webHandler = toHttpHandler(routingFunction());
        HttpHandler httpHandler = WebHttpHandlerBuilder
                .webHandler(webHandler)
                //.prependFilter(new IndexRewriteFilter())
                .build();

        Tomcat tomcat = new Tomcat();
        tomcat.setHostname("localhost");
        tomcat.setPort(9090);
        Context rootContext = tomcat.addContext("", System.getProperty("java.io.tmpdir"));
        ServletHttpHandlerAdapter servlet = new ServletHttpHandlerAdapter(httpHandler);
        Tomcat.addServlet(rootContext, "httpHandlerServlet", servlet);
        rootContext.addServletMappingDecoded("/", "httpHandlerServlet");

        TomcatWebServer server = new TomcatWebServer(tomcat);
        server.start();
        return server;

    }

    public static void main(String[] args) {

        try {
            new FunctionalWebServer().start();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
