package com.geekluxun.greateapp.spring.format;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekluxun.greateapp.spring.mail.SendMailServiceImpl;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/30 13:43
 * Description:
 */
@Component
@Data
public class FormatExample implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(FormatExample.class);


    @JsonFormat(pattern = "yyyy-MM-dd")
    //@JSONField(name = "born", format = "yyyy-MM-dd HH:mm:ss")
    private Date curDate;

    @NumberFormat(style = NumberFormat.Style.PERCENT)
    private BigDecimal money = new BigDecimal("3.11");


    public static void main(String[] argc) {
        FormatExample example = new FormatExample();
        example.init();
    }


    public void init() {
        curDate = new Date();
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.info("========== curDate:======== {}", mapper.writeValueAsString(this));
            logger.info("money:" + money);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public Date getCurDate() {
        return curDate;
    }

    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }
}
