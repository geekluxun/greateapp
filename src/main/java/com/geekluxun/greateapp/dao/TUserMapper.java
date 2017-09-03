package com.geekluxun.greateapp.dao;

import com.geekluxun.greateapp.entity.TUser;
import org.apache.ibatis.annotations.*;

/**
 * Created by luxun on 2017/9/2.
 */

public interface TUserMapper {
    @Delete({
        "delete from t_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_user (id, name, ",
        "password)",
        "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR})"
    })
    int insert(TUser record);

    int insertSelective(TUser record);

    @Select({
        "select",
        "id, name, password",
        "from t_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    TUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TUser record);

    @Update({
        "update t_user",
        "set name = #{name,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TUser record);
}