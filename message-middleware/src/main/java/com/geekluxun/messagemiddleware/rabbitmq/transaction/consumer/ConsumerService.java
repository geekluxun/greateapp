package com.geekluxun.messagemiddleware.rabbitmq.transaction.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReceiveAndReplyCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-16 16:27
 * @Description:
 * @Other:
 */
@Service
@Slf4j
public class ConsumerService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 返回响应给消息发送方（类似RPC模式）
     * @return
     */
    public Object receiveAndReply(){
        Boolean result = rabbitTemplate.receiveAndReply("myqueue2",new ReceiveAndReplyCallback() {
            @Override
            public Object handle(Object o) {
                log.info("收到消息:" + o);          
                return "我收到你的消息了";
            }
        });
        if (!result){
            log.error("接收消息错误!!!");
        }
        return "OK";
    }
}
