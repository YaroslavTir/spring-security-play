package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                    .antMatchers("/", "/home")
////                .access("hasIpAddress('127.0.0.1') or hasIpAddress('::1') or hasIpAddress('192.168.0.0/22')")
//                    .hasIpAddress("111.0.0.1")
//                    .anyRequest().authenticated()
//                    .and()
//                .httpBasic();

        http
                .authorizeRequests()
                    .requestMatchers(WebSecurityCheckingUtil
                            .allowedApiIpRequestMatcher(() -> Collections.singletonList("::1")))
                            .authenticated().anyRequest().denyAll()
                    .and()
                .httpBasic();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
}