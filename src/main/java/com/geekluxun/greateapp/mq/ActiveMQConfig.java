package com.geekluxun.greateapp.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.*;
import java.io.Serializable;

@EnableJms
@Configuration
public class ActiveMQConfig {


    public static final String topic1 = "active.mq.queue.topic.1";


    @Bean
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        /**可以自定义消息转换器*/
//        factory.setMessageConverter(new MessageConverter() {
//            @Override
//            public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
//                return session.createObjectMessage((Serializable) o);
//            }
//
//            @Override
//            public Object fromMessage(Message message) throws JMSException, MessageConversionException {
//                ObjectMessage objMessage = (ObjectMessage) message;
//                return objMessage.getObject();
//            }
//        });
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true); //设置订阅模式
        return factory;
    }


}
