package com.geekluxun.greateapp.spring.springmvc;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-29 9:11
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/uri")
@Slf4j
public class UriContorller {

    @PostMapping("/demo")
    @ApiOperation("UriComponents示例")
    @ResponseBody
    public Object handle1(HttpServletRequest request, HttpServletResponse response) {
        demo1();
        try {
            response.sendRedirect("/pet/pets/get4?name=luxun");
        } catch (IOException e) {
            log.error("异常:", e);
        }
        return "已经被重定向了";
    }


    public void demo1() {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString("http://localhost:8031/pet/get4?age=10")
                .queryParam("name", "luxun")
                .build()
                .encode();
        System.out.println("host:" + uriComponents.getHost());
        System.out.println("port:" + uriComponents.getPort());
        System.out.println("path:" + uriComponents.getPath());
        System.out.println("query:" + uriComponents.getQuery());
        System.out.println("scheme:" + uriComponents.getScheme());
        System.out.println("pathSegments:" + uriComponents.getPathSegments());
        System.out.println("fragment:" + uriComponents.getFragment());

        //expand中的值会代替{city}和{name}
        URI uri = UriComponentsBuilder.fromPath("/hotellist/{city}")
                .queryParam("name", "{name}")
                .buildAndExpand("beijing", "luxun")
                .encode().toUri();

        System.out.println("uri:" + uri);
    }


    public void demo2() {

    }
}