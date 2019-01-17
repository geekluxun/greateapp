package com.geekluxun.messagemiddleware.rabbitmq.transaction.producer;

import com.alibaba.fastjson.JSON;
import com.geekluxun.messagemiddleware.dao.TMessageMapper;
import com.geekluxun.messagemiddleware.dao.TUserMapper;
import com.geekluxun.messagemiddleware.entity.TMessage;
import com.geekluxun.messagemiddleware.entity.TUser;
import com.geekluxun.messagemiddleware.rabbitmq.transaction.constant.MsgStatus;
import com.geekluxun.messagemiddleware.rabbitmq.transaction.dto.UserInfoDto;
import com.geekluxun.messagemiddleware.util.IdGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;

import static com.geekluxun.messagemiddleware.constant.CommonConstants.RABBITMQ_QUEUE;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-17 13:50
 * @Description: 模拟业务主动方完成业务操作后发送消息通知业务被动方
 * @Other:
 */
@Slf4j
@Service
public class ProducerService {
    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private TMessageMapper messageMapper;

    @Autowired
    private RabbitTemplate template;


    @PostConstruct
    private void init() {
        /**
         * 通过broker回调确认保证消息不丢失
         */
        template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String casue) {
                String msgId = correlationData.getId();
                log.info("======消息确认回调处理开始======msgId:" + msgId + " ack:" + ack);
                TMessage message = messageMapper.selectByMessageId(msgId);
                if (ack) {
                    // 更新消息状态为已发送已确认
                    msgId = correlationData.getId();
                    message = messageMapper.selectByMessageId(msgId);
                    message.setStatus(MsgStatus.SEND_CONFIRMED.getStatus());
                    messageMapper.updateByPrimaryKeySelective(message);
                } else {
                    log.info("消息:" + msgId + " 确认失败:" + casue);
                    // 重试
                    CorrelationData data = new CorrelationData();
                    data.setId(message.getMessageId());
                    // 消息发送结束不代表broker成功收到消息,需要通过borker回调确认
                    template.convertAndSend(message.getDstQueueName(), (Object) message.getBody(), data);
                }
            }
        });
    }

    /**
     * 业务操作和消息持久化在一个事务中完成
     */
    @Transactional(rollbackFor = Exception.class)
    public void bizHandle() {

        log.info("开始业务操作...");
        TUser user = new TUser();
        user.setUserId(IdGenerateUtil.genId("U"));
        user.setName("luxun");
        user.setPassword("123");
        user.setCreateTime(new Date());
        user.setModifyTime(new Date());
        userMapper.insertSelective(user);
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserId(user.getUserId());
        userInfoDto.setName(user.getName());
        log.info("业务操作完成...");

        log.info("持久化消息开始...");
        TMessage message = new TMessage();
        message.setMessageId(IdGenerateUtil.genId("M"));
        message.setBody(JSON.toJSONString(userInfoDto));
        message.setStatus(MsgStatus.NOT_SEND.getStatus());
        message.setDstQueueName(RABBITMQ_QUEUE);
        message.setCreateTime(new Date());
        message.setModifyTime(new Date());
        message.setConcurrencyVersion(1);
        messageMapper.insert(message);
        log.info("持久化消息结束...");

        // 存放消息id，在broker回调confirm时候能匹配唯一的消息id
        log.info("消息发送开始...");

        // 消息状态变成已发送未确认
        message.setStatus(MsgStatus.SEND_NOT_CONFIRM.getStatus());
        messageMapper.updateByPrimaryKeySelective(message);
        
        CorrelationData data = new CorrelationData();
        data.setId(message.getMessageId());
        // 1、消息发送结束不代表broker成功收到消息,需要通过borker回调确认
        // 2、此发送代码必须在整个操作的最后一步，避免出现数据库事务回滚但消息却发送出去的情况！！！
        template.convertAndSend(message.getDstQueueName(), (Object) message.getBody(), data);
        log.info("消息发送结束...");
    }
}
