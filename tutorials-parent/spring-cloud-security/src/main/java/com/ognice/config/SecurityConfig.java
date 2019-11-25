package com.ognice.config;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Override
    @Bean
    /**
     *     自定义用户认证服务
     *     实现 {@link UserDetailsService}
     *     用户对象实现{@link org.springframework.security.core.userdetails.UserDetails}
     */
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.builder().passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode).username("dbfk").password("dbfk").roles("admin").build());
        return inMemoryUserDetailsManager;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.debug(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http// 激活配置
                .csrf()
                // 保护资源
                .requireCsrfProtectionMatcher(request -> true)
                // 配置token生成仓库
                .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
                .and()// 添加其他认证
                .httpBasic().disable();
    }
}
