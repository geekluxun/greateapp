package com.geekluxun.greateapp.mq.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created by luxun on 2017/10/17.
 */
@Component
public class Receiver {

    public static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch countDownLatch = new CountDownLatch(1);



    public CountDownLatch getLatch(){
        return countDownLatch;
    }


    @KafkaListener(topics = "${kafka.topic.test.1}")
    public void receive(ConsumerRecord<?, ?> record){

        logger.info("========= 收到的消息：==========", record.toString());
        countDownLatch.countDown();
    }

}
