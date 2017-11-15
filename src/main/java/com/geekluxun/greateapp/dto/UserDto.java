package com.geekluxun.greateapp.dto;

import com.geekluxun.greateapp.annotation.CheckCase;
import com.geekluxun.greateapp.annotation.ParaValidator;
import com.geekluxun.greateapp.annotation.ValidPassengerCount;
import com.geekluxun.greateapp.common.BaseDto;
import com.geekluxun.greateapp.constant.CaseMode;
import com.geekluxun.greateapp.validator.Car;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by luxun on 2017/9/2.
 */
@ParaValidator
public class UserDto extends BaseDto {
    @NotNull(message = "姓名不能为空")
    //自定义约束校验示例
    @CheckCase(value = CaseMode.UPPER, message = "姓名只能大写")
    private String name;
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}