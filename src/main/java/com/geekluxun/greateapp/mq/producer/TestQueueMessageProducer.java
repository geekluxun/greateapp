package com.geekluxun.greateapp.mq.producer;

import com.alibaba.fastjson.JSON;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.*;
import java.util.Random;

/**
 * Created by luxun on 2017/10/15.
 */
@Component
public class TestQueueMessageProducer implements MessageListener
{
    public final static Logger logger = LoggerFactory.getLogger(TestQueueMessageProducer.class);


    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${active.mq.destination.queue.test}")
    String queueTest;

    @Autowired
    @Qualifier("testQueueDestinationReply")
    ActiveMQQueue replyDestination;

    @PostConstruct
    public void init(){
        Session session;
        MessageConsumer consumer;
        try {
            session = jmsTemplate.getConnectionFactory().createConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
            consumer = session.createConsumer(replyDestination);
            consumer.setMessageListener(this);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void sendMsg(Object msg){

        final String json = JSON.toJSONString(msg);

        jmsTemplate.send(queueTest, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message msg = session.createTextMessage(json);
                logger.info("sendMsg Id:" + msg.getJMSMessageID());
                msg.setJMSCorrelationID(Math.abs(new Random().nextLong()) + "");
                msg.setJMSReplyTo(replyDestination);
                return msg;
            }
        });
    }

    @Override
    public void onMessage(Message message) {

        if (message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            String msg = null;
            String msgId = null;
            try {
                msg = textMessage.getText();
                msgId = textMessage.getJMSMessageID();
            } catch (JMSException e) {
                logger.error("======== onMessage() =========", e);
            }


            logger.info("=========  TestQueueMessageProducer recvReplyMsg id: " + msgId + " content:" + msg);

        }

    }

}
