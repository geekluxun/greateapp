package com.geekluxun.greateapp.spring.mail;

import java.util.List;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/29 10:02
 * Description:
 */
public interface SendMailService {
    void send(String subject, String content, List<String> receivers, List<String> cc, List<String> filePath);
}
