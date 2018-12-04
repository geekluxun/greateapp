package com.geekluxun.greateapp.validator;

import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by luxun on 2017/11/11.
 */
public class Gear {

    @Min(10)
    private Integer power;

    @DecimalMax(value = "1000000", message = "最大价格100万", inclusive = false)
    private BigDecimal price;


    //最大整数位数和小数小数位数
    @Digits(integer = 100, fraction = 2, message = "最大整数10位小数2位")
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
    @Size(min = 2, max = 4, message = "窗户最小2个，最大4个")
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


    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Date getBaoxiuDate() {
        return baoxiuDate;
    }

    public void setBaoxiuDate(Date baoxiuDate) {
        this.baoxiuDate = baoxiuDate;
    }

    public Date getOutFactory() {
        return outFactory;
    }

    public void setOutFactory(Date outFactory) {
        this.outFactory = outFactory;
    }

    public Boolean getMaster() {
        return master;
    }

    public void setMaster(Boolean master) {
        this.master = master;
    }

    public String getProductSerial() {
        return productSerial;
    }

    public void setProductSerial(String productSerial) {
        this.productSerial = productSerial;
    }

    public List getWindows() {
        return windows;
    }

    public void setWindows(List windows) {
        this.windows = windows;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
