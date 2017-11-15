package com.geekluxun.greateapp.validator;

import com.geekluxun.greateapp.annotation.ValidPassengerCount;
import org.hibernate.validator.constraints.NotBlank;
import org.junit.Assert;

import javax.validation.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by luxun on 2017/11/11.
 */
//定义类级的约束校验，使用场景：约束条件由多个属性共同组成 ，校验时需要调用validator.validate(car)方法
@ValidPassengerCount(message = "乘车人数不对")
public class Car {

    @NotBlank(message = "品牌不能为空")
    private String brand;

    @NotNull(message = "引擎不能为空")
    private Gear gearList;


    private  Integer passengers;

    private Integer seatCount;


    public Car() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(@NotNull String brand) {
        this.brand = brand;
    }


    public Integer getPassengers() {
        return passengers;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setPassengers(Integer passengers) {
        this.passengers = passengers;
    }


    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Gear getGearList() {
        return gearList;
    }

    public void setGearList(Gear gearList) {
        this.gearList = gearList;
    }

    public static void main(String[] argc){
        Car car = new Car();
        car.setBrand(null);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);

        Assert.assertEquals( 2, constraintViolations.size() );
        Assert.assertEquals("品牌不能为空",constraintViolations.iterator().next().getMessage());

        //验证某一属性
        constraintViolations = validator.validateProperty(car,"gearList");
        Assert.assertEquals(1, constraintViolations.size());

        //验证某一属性的值
        constraintViolations = validator.validateValue(Car.class,"brand",null);
        Assert.assertEquals(1, constraintViolations.size());

    }


}
