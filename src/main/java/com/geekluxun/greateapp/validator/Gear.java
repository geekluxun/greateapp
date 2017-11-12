package com.geekluxun.greateapp.validator;

import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by luxun on 2017/11/11.
 */
public class Gear{

    @Min(10)
    private Integer power;

    @DecimalMax(value = "1000000",message = "最大价格100万",inclusive = false)
    private BigDecimal price;


    //最大整数位数和小数小数位数
    @Digits(integer = 100,fraction = 2, message = "最大整数10位小数2位")
    private Integer tax;

    @Future(message = "保修期必须大于当前日期")
    private Date baoxiuDate;



    @Past(message = "出厂日期必须小于现在")
    private Date outFactory;

    @AssertTrue(message = "必须是主引擎")
    private Boolean master;


    @Pattern(regexp = "dfasd", message = "产品编号必须符合正则表达式")
    private String productSerial;

    @NotEmpty(message = "不能为空")
    @Size(min = 2,max = 4, message = "窗户最小2个，最大4个")
    private List windows;

    @CreditCardNumber(message = "信用卡")
    private String creditCard;

    @Email
    private String email;

    @Range(min = 0L, max = 33)
    private Integer value;

    @Length(min = 1, max = 100)
    private String desc;

    @URL
    private String url;

    public Boolean getMaster() {
        return master;
    }

    public Integer getPower() {
        return power;
    }

    public void setMaster(Boolean master) {
        this.master = master;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
}
