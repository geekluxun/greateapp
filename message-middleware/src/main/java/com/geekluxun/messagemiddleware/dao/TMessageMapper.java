package com.geekluxun.messagemiddleware.dao;

import com.geekluxun.messagemiddleware.entity.TMessage;
import org.apache.ibatis.annotations.Param;

public interface TMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TMessage record);

    int insertSelective(TMessage record);

    TMessage selectByPrimaryKey(Long id);

    TMessage selectByMessageId(@Param("messageId") String messageId);

    int updateByPrimaryKeySelective(TMessage record);

    int updateByPrimaryKey(TMessage record);
}