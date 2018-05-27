package com.geekluxun.greateapp.spring.mail;

import com.geekluxun.greateapp.dto.MailSendDto;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/29 10:02
 * Description:
 */
public interface SendMailService {
    void send(MailSendDto mailConfigDto);
}
