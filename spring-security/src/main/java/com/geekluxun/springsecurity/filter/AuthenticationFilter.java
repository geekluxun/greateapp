package com.geekluxun.springsecurity.filter;

import com.geekluxun.springsecurity.service.TokenAuthenticationService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-21 17:22
 * @Description:
 * @Other:
 */
public class AuthenticationFilter extends BasicAuthenticationFilter {

    private RedisTemplate template;


    public AuthenticationFilter(AuthenticationManager authenticationManager, RedisTemplate template) {
        super(authenticationManager);
        this.template = template;
    }


    /**
     * 在此方法中检验客户端请求头中的token,
     * 如果存在并合法,就把token中的信息封装到 Authentication 类型的对象中,
     * 最后使用  SecurityContextHolder.getContext().setAuthentication(authentication); 改变或删除当前已经验证的 pricipal
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authentication authentication = TokenAuthenticationService.getAuthentication(request, template);

        //判断是否有token
        if (authentication == null) {
            //chain.doFilter(request, response);
            logger.warn("请先登录....");
            return;
        }
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //放行
        chain.doFilter(request, response);
    }
    
}