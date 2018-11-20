package com.geekluxun.greateapp.mq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;

@EnableJms
@Configuration
public class ActiveMQConfig {


    public static final String topic1 = "active.mq.queue.topic.1";

    public static final String topic2 = "active.mq.queue.topic.2";


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
        factory.setConcurrency("3-10");//core pool 3个线程，最大线程10个 设置consumer到并发度
        factory.setErrorHandler(new ErrorHandler() {
            @Override
            public void handleError(Throwable throwable) {

            }
        });


        /**设置服务质量，可能是高版支持*/
//        QosSettings replyQosSettings = new ReplyQosSettings();
//        replyQosSettings.setPriority(2);
//        replyQosSettings.setTimeToLive(10000);

        return factory;
    }


}
