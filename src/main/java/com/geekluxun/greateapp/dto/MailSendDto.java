package com.geekluxun.greateapp.dto;


import java.util.List;

/**
 * Created by luxun on 2018/5/27.
 */
public class MailSendDto extends BaseDto {

    private String subject;
    private String content;
    private String from;
    private String fromPassword;
    private List<String> to;
    private List<String> cc;
    private List<String> attachFilePaths;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromPassword() {
        return fromPassword;
    }

    public void setFromPassword(String fromPassword) {
        this.fromPassword = fromPassword;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getAttachFilePaths() {
        return attachFilePaths;
    }

    public void setAttachFilePaths(List<String> attachFilePaths) {
        this.attachFilePaths = attachFilePaths;
    }

}
