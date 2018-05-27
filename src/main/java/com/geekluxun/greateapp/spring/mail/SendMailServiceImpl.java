package com.geekluxun.greateapp.spring.mail;

import com.geekluxun.greateapp.dto.MailSendDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

/**
 * Project: greateapp
 * @Author: luxun
 * Date: 2018/1/29 10:03
 * Description:
 */
@Service(value = "SendMailService")
public class SendMailServiceImpl implements SendMailService {

    private static final Logger logger = LoggerFactory.getLogger(SendMailServiceImpl.class);



    /**
     *
     * @param mailSendDto
     */
    @Override
    public void send(MailSendDto mailSendDto) {

        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setUsername(mailSendDto.getFrom());
        sender.setPassword(mailSendDto.getFromPassword());

        Properties pp = new Properties();
        // 邮箱验证
        pp.put("mail.smtp.auth", true);
        pp.put("mail.smtp.starttls.enable", true);
        pp.put("mail.smtp.host", "smtp.partner.outlook.cn");
        pp.put("mail.smtp.port", 587);

        sender.setJavaMailProperties(pp);


        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);

            helper.setTo((String[]) mailSendDto.getTo().toArray());
            helper.setText(mailSendDto.getContent());
            helper.setFrom(mailSendDto.getFrom());
            helper.setSubject(mailSendDto.getSubject());
            if (mailSendDto.getCc() != null){
                helper.setCc((String[]) mailSendDto.getCc().toArray());
            }

            if (mailSendDto.getAttachFilePaths() != null){
                for (String filePath : mailSendDto.getAttachFilePaths()){
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
