package com.geekluxun.greateapp.mq.activemq.producer;

import com.geekluxun.greateapp.mq.ActiveMQConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/24 17:35
 * Description:
 * 发布订阅模式 发布消息
 */
@Component
public class TopicProducer {


    @Autowired
    JmsTemplate jmsTemplate;


    @PostConstruct
    public void init() {

    }


    public void sendMsg(Object msg) {
        /** 发布订阅模式必须要设置此属性！！！*/
        jmsTemplate.setPubSubDomain(true);

//        jmsTemplate.send(ActiveMQConfig.topic1, new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                Message msg = session.createTextMessage("hello!!!");
//                return msg;
//            }
//        });

        jmsTemplate.convertAndSend(ActiveMQConfig.topic1, msg);
    }
}
