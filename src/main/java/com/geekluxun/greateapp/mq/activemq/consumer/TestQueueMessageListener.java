package com.geekluxun.greateapp.mq.activemq.consumer;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.*;

/**
 * Created by luxun on 2017/10/15.
 */
public class TestQueueMessageListener implements SessionAwareMessageListener {
    static final Logger logger = LoggerFactory.getLogger(TestQueueMessageListener.class);


    @Autowired
    @Qualifier("testQueueDestinationReply")
    ActiveMQQueue replyDestination;


    /**
     * SessionAwareMessageListener 的onMessage 和MessageListener的 的onMessage方法不同之处在于多了一个Session，可以
     * 给生产者发送反馈消息
     *
     * @param message
     * @param session
     */
    @Override
    public void onMessage(Message message, Session session) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            String msg = null;
            String msgId = null;
            try {
                msg = textMessage.getText();
                msgId = textMessage.getJMSMessageID();
            } catch (JMSException e) {
                logger.error("======== onMessage() =========", e);
            }

            logger.info("========= recvMsg id: " + msgId + " content:" + msg);

            // 发送响应消息给生产者
            try {
                /** 通过replyDestination 队列给生产者发送反馈消息*/
                MessageProducer producer = session.createProducer(replyDestination);
                TextMessage textMessage1 = session.createTextMessage();
                textMessage1.setText("我收到你的消息了！！！");
                /** 把消息id返回给生产者*/
                textMessage1.setJMSCorrelationID(message.getJMSCorrelationID());
                producer.send(message.getJMSReplyTo(), textMessage1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
