package com.geekluxun.greateapp.spring.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

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
     * @param file
     * @return
     */
    @PostMapping("/form")
    public String handleFormUpload (@RequestPart("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                System.out.println("接收到的文件内容是:");
                while (reader.ready()){
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
