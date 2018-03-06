package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.constant.TStatus;
import com.geekluxun.greateapp.dao.RedpacketAccountMapper;
import com.geekluxun.greateapp.dao.TradeOrderMapper;
import com.geekluxun.greateapp.dto.RedPacketTradeOrderDto;
import com.geekluxun.greateapp.entity.RedpacketAccount;
import com.geekluxun.greateapp.entity.TradeOrder;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.dubbo.context.DubboTransactionContextEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

/**
 * Created by luxun on 2018/03/05.
 */
@Service("redPacketTradeOrderService")
public class RedPacketTradeOrderServiceImpl implements RedPacketTradeOrderService {

    @Autowired
    RedpacketAccountMapper redpacketAccountMapper;

    @Autowired
    TradeOrderMapper tradeOrderMapper;


    /**
     * 尝试，如果成功执行confirmRecord方法,如果失败执行cancelRecord方法.
     * @param tradeOrderDto
     * @return
     */
    @Override
    @Compensable(confirmMethod = "confirmRecord", cancelMethod = "cancelRecord", transactionContextEditor = DubboTransactionContextEditor.class)
    @Transactional
    public String record(RedPacketTradeOrderDto tradeOrderDto) {

        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("red packet try record called. time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

        TradeOrder foundTradeOrder = tradeOrderMapper.findByMerchantOrderNo(tradeOrderDto.getMerchantOrderNo());

        //check if trade order has been recorded, if yes, return success directly.
        if (foundTradeOrder == null) {

            TradeOrder tradeOrder = new TradeOrder();
            tradeOrder.setSelfUserId(tradeOrderDto.getSelfUserId());
            tradeOrder.setOppositeUserId(tradeOrderDto.getOppositeUserId());
            tradeOrder.setMerchantOrderNo(tradeOrderDto.getMerchantOrderNo());
            tradeOrder.setAmount(tradeOrderDto.getAmount());

            try {
                /**
                 * 插入这笔订单
                 */
                tradeOrderMapper.insert(tradeOrder);

                /**
                 * 自己的红包账户减去amount金额
                 */
                RedpacketAccount transferFromAccount = redpacketAccountMapper.findByUserId(tradeOrderDto.getSelfUserId());

                transferFromAccount.setBalanceAmount(transferFromAccount.getBalanceAmount().subtract(tradeOrderDto.getAmount()));

                redpacketAccountMapper.insertSelective(transferFromAccount);
            } catch (DataIntegrityViolationException e) {
                //this exception may happen when insert trade order concurrently, if happened, ignore this insert operation.
            }
        }

        return "success";
    }

    /**
     * 确认
     * @param tradeOrderDto
     */
    @Transactional
    public void confirmRecord(RedPacketTradeOrderDto tradeOrderDto) {

        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("red packet confirm record called. time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

        TradeOrder tradeOrder = tradeOrderMapper.findByMerchantOrderNo(Long.valueOf(tradeOrderDto.getMerchantOrderNo()));

        //check if the trade order status is DRAFT, if yes, return directly, ensure idempotency.
        if (tradeOrder != null && tradeOrder.getStatus().equals(TStatus.DRAFT.toString())) {
            tradeOrder.setStatus(TStatus.CONFIRM.toString());
            tradeOrderMapper.updateByPrimaryKeySelective(tradeOrder);

            RedpacketAccount transferToAccount = redpacketAccountMapper.findByUserId(tradeOrderDto.getOppositeUserId());

            //对方账户增加amount金额
            transferToAccount.setBalanceAmount(transferToAccount.getBalanceAmount().add(tradeOrderDto.getAmount()));


            redpacketAccountMapper.insertSelective(transferToAccount);
        }
    }

    /**
     * 取消事务，其本质上做相反的操作,也就是回滚
     * @param tradeOrderDto
     */
    @Transactional
    public void cancelRecord(RedPacketTradeOrderDto tradeOrderDto) {

        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("red packet cancel record called. time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

        TradeOrder tradeOrder = tradeOrderMapper.findByMerchantOrderNo(tradeOrderDto.getMerchantOrderNo());

        //check if the trade order status is DRAFT, if yes, return directly, ensure idempotency.
        if (null != tradeOrder && TStatus.DRAFT.toString().equals(tradeOrder.getStatus())) {
            //tradeOrder.cancel();
            tradeOrder.setStatus(TStatus.CANCEL.toString());
            tradeOrderMapper.updateByPrimaryKeySelective(tradeOrder);

            /**
             * 自己账户再增加之前扣减的金额
             */
            RedpacketAccount capitalAccount = redpacketAccountMapper.findByUserId(tradeOrderDto.getSelfUserId());

            capitalAccount.setBalanceAmount(capitalAccount.getBalanceAmount().add(tradeOrderDto.getAmount()));

            redpacketAccountMapper.updateByPrimaryKeySelective(capitalAccount);
        }
    }
}
