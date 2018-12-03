package com.geekluxun.greateapp.spring.springmvc;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.TimeUnit;

/**
 * Copyright,2018-2019,geekluxunb Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-29 20:27
 * @Description:
 * @Other:
 */
@Controller
@Slf4j
@RequestMapping("/cache")
public class HttpCacheExample {

    @GetMapping("/pet")
    @ApiOperation(("httpCache示例"))
    public ResponseEntity<Pet> handle(WebRequest webRequest) {
        String eTag = webRequest.getHeader("If-None-Match");
        System.out.println("eTag:" + eTag);
        Pet pet = new Pet();
        pet.setName("luxun");
        pet.setColor("blue");
        pet.setAge(1012);

        // 缓存20秒  响应头中有"cache-control": "max-age=20"
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(20, TimeUnit.SECONDS))
                .eTag("dd") // lastModified is also available
                .body(pet);
    }

    @GetMapping("/etag")
    @ApiOperation(("eTag示例"))
    @ResponseBody
    public Object Objecthandle2(WebRequest webRequest) {
        String eTag = webRequest.getHeader("If-None-Match");
        System.out.println("eTag:" + eTag);
        Pet pet = new Pet();
        pet.setName("luxun");
        pet.setColor("blue");
        pet.setAge(1012);
        return pet;
    }
}
