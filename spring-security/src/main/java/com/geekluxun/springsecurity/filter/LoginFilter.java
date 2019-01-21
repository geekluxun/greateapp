package com.geekluxun.springsecurity.filter;

import com.geekluxun.springsecurity.security.GrantedAuthorityImpl;
import com.geekluxun.springsecurity.service.TokenAuthenticationService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-21 17:22
 * @Description:
 * @Other:
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {


    private AuthenticationManager authenticationManager;

    private RedisTemplate template;


    /**
     * 
     * @param authenticationManager
     * @param template
     */
    public LoginFilter(AuthenticationManager authenticationManager, RedisTemplate template) {
        super(new AntPathRequestMatcher("/login", "POST"));
        this.authenticationManager = authenticationManager;
        this.template = template;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //得到用户登陆信息,并封装到 Authentication 中,供自定义用户组件使用.
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        authorities.add(grantedAuthority);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, authorities);

        authenticationManager.authenticate(authenticationToken);
        return authenticationToken;
    }


    /**
     * 登陆成功后,此方法会被调用,因此我们可以在次方法中生成token,并返回给客户端
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) {

        TokenAuthenticationService.addAuthenticatiotoHttpHeader(response, authResult, template);
    }
}
