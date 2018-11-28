package com.geekluxun.greateapp.spring.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-27 13:42
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {

    /**
     * 文件上传示例
     *
     * @param file
     * @return
     */
    @PostMapping("/form")
    public String handleFormUpload(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            try {
                System.out.println(request.getHeaderNames());
                Enumeration<String> headerNames = request.getHeaderNames();
                while (headerNames.hasMoreElements()) {
                    String headerName = headerNames.nextElement();
                    System.out.println("headerName:" + headerName + " headerValue:" + request.getHeader(headerName));
                }

                byte[] bytes = file.getBytes();
                InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
                System.out.println("字节流转成字符流使用的编码方式：" + inputStreamReader.getEncoding());
                BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
                System.out.println("接收到的文件内容是:");
                while (reader.ready()) {
                    System.out.println(reader.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "redirect:uploadSuccess";
        }
        return "redirect:uploadFailure";
    }
}
