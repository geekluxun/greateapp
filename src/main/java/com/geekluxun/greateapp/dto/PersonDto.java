package com.geekluxun.greateapp.dto;

import com.geekluxun.greateapp.annotation.CheckCase;
import com.geekluxun.greateapp.annotation.ParaValidator;
import com.geekluxun.greateapp.common.BaseDto;
import com.geekluxun.greateapp.constant.CaseMode;
import com.geekluxun.greateapp.validator.Car;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by luxun on 2017/11/15.
 */

@ParaValidator
public class PersonDto extends BaseDto {

    //为了使car里面的约束校验生效，此处必须加上上@Valid,表示级联，否则car里面的约束忽略不校验
    //此处首先加@NotNull校验，如果car为null，car内部的校验不会生效！！！
    @Valid
    @NotNull(message = "car不能为空")
    private Car car;


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}

