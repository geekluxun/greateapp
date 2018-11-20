package com.geekluxun.greateapp.util;

/**
 * Created by luxun on 2018/1/11.
 */

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;

import com.sun.mail.util.MailSSLSocketFactory;

import java.util.List;
import java.util.Properties;

/**
 * 发送邮件工具类
 */
public class MailUtil {

    private static Multipart multipart = new MimeMultipart();


    public static void sendMail(String title, String content, String sendUser,
                                String pwd, String recipientTo, List recevieCopy,
                                boolean fileFlag, List<String> filePath) throws Exception {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        /*
         * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
         * mail.user / mail.from
         */
        // 表示SMTP发送邮件，需要进行身份验证
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.host", "smtp.qiye.163.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        //props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.port", "465");
        // 发件人的账号
        props.put("mail.user", sendUser);
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", pwd);

        //发送邮件的代码中添加证书信任配置
        MailSSLSocketFactory mailSSLSocketFactory = new MailSSLSocketFactory();
        mailSSLSocketFactory.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.ssl.socketFactory", mailSSLSocketFactory);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人
        InternetAddress to = new InternetAddress(recipientTo);
        message.setRecipient(RecipientType.TO, to);

        // 设置抄送
        Address[] ccAdresses = null;
        if (recevieCopy != null && recevieCopy.size() > 0) {
            // 为每个邮件接收者创建一个地址
            ccAdresses = new InternetAddress[recevieCopy.size()];
            for (int i = 0; i < recevieCopy.size(); i++) {
                ccAdresses[i] = new InternetAddress(recevieCopy.get(i).toString());
            }
            message.setRecipients(RecipientType.CC, ccAdresses);
        }
//        InternetAddress[] internetAddressCC = new InternetAddress().parse(ccAdresses);

        // 设置密送，其他的收件人不能看到密送的邮件地址
//        InternetAddress bcc = new InternetAddress("aaaaa@163.com");
//        message.setRecipient(RecipientType.CC, bcc);

        // 设置邮件标题
        message.setSubject(MimeUtility.encodeText(title, MimeUtility.mimeCharset("utf-8"), null));

        // Set file
        //是否有附件
        if (fileFlag) {
            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(content, "text/html;charset=utf-8");
            multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);
            if (null != filePath) {
                for (int i = 0; i < filePath.size(); i++) {
                    String filename = filePath.get(i);
                    BodyPart bodyPart1 = new MimeBodyPart();
                    FileDataSource fileDataSource = new FileDataSource("/tmp/" +
                            filename);
                    bodyPart1.setDataHandler(new DataHandler(fileDataSource));
                    bodyPart1.setFileName(fileDataSource.getName());
                    multipart.addBodyPart(bodyPart1);
                }
            }

            message.setContent(multipart);
            message.saveChanges();
        } else {
            // 设置邮件的内容体
            message.setContent(content, "text/html;charset=UTF-8");
        }
        // 发送邮件
        Transport.send(message);

    }
}