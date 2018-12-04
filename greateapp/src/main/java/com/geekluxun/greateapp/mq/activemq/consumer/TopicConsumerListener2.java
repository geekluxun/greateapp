package com.geekluxun.greateapp.mq.activemq.consumer;

import com.alibaba.fastjson.JSON;
import com.geekluxun.greateapp.dto.TestDto;
import com.geekluxun.greateapp.mq.ActiveMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.Session;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/24 17:30
 * Description: 发布订阅模式 订阅者2
 */
@Component
public class TopicConsumerListener2 {
    private static final Logger logger = LoggerFactory.getLogger(TopicConsumerListener2.class);


    /**
     * 发送方把TestDto转换成消息发送过来，此处可使用@Payload发序列化成dto
     *
     * @param message
     * @param testDto
     * @param headers
     * @param session
     */
    @JmsListener(destination = ActiveMQConfig.topic1, containerFactory = "topicListenerFactory")
    public void receive(Message message,
                        @Payload TestDto testDto,
                        @Headers MessageHeaders headers,
                        Session session) {

        logger.info("订阅者2收到消息了！！！" + JSON.toJSONString(testDto));
    }
}