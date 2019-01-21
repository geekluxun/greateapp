package com.geekluxun.springsecurity.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-21 17:22
 * @Description:
 * @Other:
 */

@Slf4j
public class TokenAuthenticationService {

    private static final String TOKEN_HEADER = "AccessToken";

    /**
     * @param response
     * @param authentication
     * @param redisTemplate
     */
    public static void addAuthenticatiotoHttpHeader(HttpServletResponse response, Authentication authentication, RedisTemplate redisTemplate) {

        String token = "T" + RandomStringUtils.randomNumeric(15);

        String json = JSON.toJSONString((authentication));
        //把token设置到响应头中去
        redisTemplate.opsForValue().set(token, json);

        response.addHeader(TOKEN_HEADER, token);

    }

    /**
     * 从请求头中解析出 Authentication
     *
     * @param request
     * @return
     */
    public static Authentication getAuthentication(HttpServletRequest request, RedisTemplate redisTemplate) {
        // 从Header中拿到token
        String token = request.getHeader(TOKEN_HEADER);
        if (token == null) {
            return null;
        }

        String json = (String) redisTemplate.opsForValue().get(token);
        UsernamePasswordAuthenticationToken  authentication = JSON.parseObject(json, UsernamePasswordAuthenticationToken.class);
        List arrayList = new ArrayList<GrantedAuthority>();
        arrayList.add( new SimpleGrantedAuthority("ROLE_ADMIN"));
       
        //TODO  反序列authorities无法成功，暂时用临时代码写死
        authentication = new UsernamePasswordAuthenticationToken("luxun123", "123", arrayList);
        if (authentication == null){
            log.warn("========token已失效=======");
            return null;
        }
        return authentication;
    }
}
