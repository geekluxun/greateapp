package com.geekluxun.greateapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.geekluxun.greateapp.annotation.CheckCase;
import com.geekluxun.greateapp.annotation.ParaValidator;
import com.geekluxun.greateapp.annotation.ValidPassengerCount;
import com.geekluxun.greateapp.common.BaseDto;
import com.geekluxun.greateapp.constant.CaseMode;
import com.geekluxun.greateapp.validator.Car;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by luxun on 2017/9/2.
 */
@ParaValidator
@ApiModel(value = "UserDto")
public class UserDto extends BaseDto {
    @NotNull(message = "姓名不能为空")
    @CheckCase(value = CaseMode.UPPER, message = "姓名只能大写") //自定义约束校验示例
    @ApiModelProperty(value = "用户姓名", required = true)
    private String name;

    @ApiModelProperty(value = "用户密码", required = true)
    private String password;

    @ApiModelProperty(value = "年龄", required = false)
    private Integer age;

    @ApiModelProperty(value = "出生日期", dataType = "java.util.Date", required = true)
    //@JsonFormat(pattern = "yyyy-MM-dd")
    //@JsonProperty(value = "birthday")
    private Date both;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBoth() {
        return both;
    }

    public void setBoth(Date both) {
        this.both = both;
    }
}