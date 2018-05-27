package com.geekluxun.greateapp.spring.mail;

import com.geekluxun.greateapp.controller.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/29 10:03
 * Description:
 */
@Service(value = "SendMailService")
public class SendMailServiceImpl implements SendMailService {


    private JavaMailSenderImpl sender;

    private static final Logger logger = LoggerFactory.getLogger(SendMailServiceImpl.class);


    final String from = "noreply@xinxindai.com";
    final String passwd = "Yxwlhrqwop78nm";


    @PostConstruct
    private void init(){
        sender = new JavaMailSenderImpl();
        //sender.setHost("smtp.163.com");
        //sender.setUsername("geekluxun@163.com");
        /** 邮箱的密码*/
        //sender.setPassword("******");

        sender.setUsername(from);
        sender.setPassword(passwd);

        Properties pp = new Properties();
        pp.put("mail.smtp.auth", true);// 邮箱验证
        pp.put("mail.smtp.starttls.enable", true);
        pp.put("mail.smtp.host", "smtp.partner.outlook.cn");
        pp.put("mail.smtp.port", 587);
        sender.setJavaMailProperties(pp);
    }

    /**
     * @param subject
     * @param content
     * @param receivers
     * @param cc
     * @param filePaths
     */
    public void send(String subject, String content, List<String> receivers, List<String> cc, List<String> filePaths) {

        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);

            helper.setTo((String[]) receivers.toArray());
            helper.setText(content);
            helper.setFrom(from);
            helper.setSubject(subject);
            if (cc != null){
                helper.setCc((String[]) cc.toArray());
            }

            if (filePaths != null){
                for (String filePath : filePaths){
                    FileSystemResource file = new FileSystemResource(new File(filePath));
                    helper.addAttachment(file.getFilename(), file);
                }
            }

            sender.send(message);
        } catch (Exception e) {
            logger.error("===== 发送邮件：======", e);
            throw new RuntimeException(e);
        }

    }

}
