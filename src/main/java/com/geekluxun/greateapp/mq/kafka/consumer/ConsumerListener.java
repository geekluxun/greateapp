package com.geekluxun.greateapp.mq.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

/**
 * Created by luxun on 2017/10/19.
 */
public final class ConsumerListener implements MessageListener<String,String> {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerListener.class);


    @Override
    public void onMessage(ConsumerRecord<String, String> stringStringConsumerRecord) {
        logger.info("消息： " + stringStringConsumerRecord);
        logger.info("收到了消息了！！！");
    }


    public void processMessage(){
        logger.info("我通过第2种方式收到了消息了！！！");
    }

}