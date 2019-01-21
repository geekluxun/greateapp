package com.geekluxun.springsecurity.config;

import com.geekluxun.springsecurity.filter.AuthenticationFilter;
import com.geekluxun.springsecurity.filter.LoginFilter;
import com.geekluxun.springsecurity.service.CustomAuthenticationProvider;
import com.geekluxun.springsecurity.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-18 13:38
 * @Description:
 * @Other:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
//        builder
//            .inMemoryAuthentication()
//            .withUser("user")
//            .password("password")
//            .roles("USER")
//            .and()
//            .withUser("admin")
//            .password("admin")
//            .roles("USER", "ADMIN")
        builder.authenticationProvider(new CustomAuthenticationProvider(userDetailsService, passwordEncoder()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//        http
//            .authorizeRequests()
//            .anyRequest()
//            .authenticated()
//            .and()
//            .formLogin().and()
//            .httpBasic();

//        http.csrf().disable();
//
//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/loginsuccess")
//                .permitAll()
//                .and() // 登录成功跳转路径url(4)
//                .logout()
//                .permitAll();
//
//        http.logout().logoutSuccessUrl("/"); // 退出默认跳转页面 (5)

        //禁用 csrf
        http.cors().and().csrf().disable().authorizeRequests()
                //允许以下请求
                .antMatchers("/page1").permitAll()
                // 所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                //验证登陆
                .addFilterBefore(new LoginFilter(authenticationManager(), redisTemplate), UsernamePasswordAuthenticationFilter.class)
                //验证token
                .addFilterBefore(new AuthenticationFilter(authenticationManager(), redisTemplate), UsernamePasswordAuthenticationFilter.class);


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/resources/**");
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
    

}
