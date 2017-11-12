package com.geekluxun.greateapp.validator;

import org.hibernate.validator.constraints.NotBlank;
import org.junit.Assert;

import javax.validation.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by luxun on 2017/11/11.
 */
public class Car {

    @NotBlank(message = "品牌不能为空")
    private String brand;

    @NotNull
    private Gear gearList;

    public Car() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(@NotNull String brand) {
        this.brand = brand;
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
