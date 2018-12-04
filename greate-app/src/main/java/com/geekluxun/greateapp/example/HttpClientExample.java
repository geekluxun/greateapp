package com.geekluxun.greateapp.example;

import com.alibaba.fastjson.JSON;
import com.geekluxun.greateapp.dto.UserDto;
import com.geekluxun.greateapp.util.httpclient.HttpClientService;
import com.geekluxun.greateapp.util.httpclient.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/2/2 16:20
 * Description:
 */
@Service
public class HttpClientExample {

    @Autowired
    HttpClientService httpClientService;

    public void testPost() {
        String url = "http://localhost:8031/main.json";
        Map headers = new HashMap<String, String>();

        headers.put("myheader", "hello");
        UserDto userDto = new UserDto();
        userDto.setName("luxun");
        userDto.setPassword("112233");
        userDto.setAge(100);
        userDto.setBoth(new Date());

        try {
            HttpResult response = httpClientService.doPost(url, headers, JSON.toJSONString(userDto));
            System.out.println("POST请求响应结果:" + response.getData());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
