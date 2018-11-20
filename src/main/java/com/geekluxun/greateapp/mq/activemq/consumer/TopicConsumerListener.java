package com.geekluxun.greateapp.mq.activemq.consumer;

import com.alibaba.fastjson.JSON;
import com.geekluxun.greateapp.dto.MqDto;
import com.geekluxun.greateapp.dto.TestDto;
import com.geekluxun.greateapp.mq.ActiveMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.JmsResponse;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.jms.Session;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/24 17:30
 * Description:
 * 发布订阅模式 订阅者1
 */
@Component
public class TopicConsumerListener {
    private static final Logger logger = LoggerFactory.getLogger(TopicConsumerListener.class);

    /**
     * 发送方把TestDto转换成消息发送过来，此处可使用@Payload发序列化成dto
     *
     * @param message
     * @param testDto
     * @param headers
     * @param session
     */
    @JmsListener(destination = ActiveMQConfig.topic1, containerFactory = "topicListenerFactory")
    @SendTo("response") //给生产者发送响应
    public MqDto receive(Message message,
                         @Payload TestDto testDto,
                         @Headers MessageHeaders headers,
                         Session session) {

        logger.info("订阅者1收到消息了！！！！" + JSON.toJSONString(testDto));
        return new MqDto();
    }


    /**
     * 带消息头带响应
     *
     * @param message
     * @param testDto
     * @param headers
     * @param session
     * @return
     */
    @JmsListener(destination = ActiveMQConfig.topic2, containerFactory = "topicListenerFactory")
    @SendTo("response") //给生产者发送响应
    public Message<MqDto> receive2(Message message,
                                   @Payload TestDto testDto,
                                   @Headers MessageHeaders headers,
                                   Session session) {

        logger.info("订阅者1收到消息了！！！！" + JSON.toJSONString(testDto));
        return MessageBuilder
                .withPayload(new MqDto())
                .setHeader("code", 1234)
                .build();
    }


    /**
     * 响应queue是动态指定的示例
     *
     * @param message
     * @param testDto
     * @param headers
     * @param session
     * @return
     */
    @JmsListener(destination = ActiveMQConfig.topic2, containerFactory = "topicListenerFactory")
    @SendTo("response") //给生产者发送响应
    public JmsResponse<Message<MqDto>> receive3(Message message,
                                                @Payload TestDto testDto,
                                                @Headers MessageHeaders headers,
                                                Session session) {

        logger.info("订阅者1收到消息了！！！！" + JSON.toJSONString(testDto));

        Message<MqDto> response = MessageBuilder
                .withPayload(new MqDto())
                .setHeader("code", 1234)
                .build();
        return JmsResponse.forQueue(response, "status");
    }
}
