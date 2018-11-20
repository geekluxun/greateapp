package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.BaseTest;
import com.geekluxun.greateapp.dto.PersonDto;
import com.geekluxun.greateapp.dto.UserDto;
import com.geekluxun.greateapp.execption.ParaValidException;
import com.geekluxun.greateapp.service.UserService.UserService;
import com.geekluxun.greateapp.validator.Car;
import com.geekluxun.greateapp.validator.Gear;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.fail;

/**
 * Created by luxun on 2017/11/14.
 */

public class ValidatorTest extends BaseTest {

    @Autowired
    ValidatorService validatorService;


    @Test
    public void testValid2() {

        Boolean exception = false;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        //时间相等校验不通过 会抛出异常
        try {
            validatorService.testValidate2(calendar.getTime(), calendar.getTime());
        } catch (ParaValidException e) {
            logger.error("======= testValid ======= ", e);
            exception = true;
        } finally {
            Assert.assertTrue(exception);
        }


        //时间不等校验通过
        exception = false;
        calendar.add(Calendar.YEAR, -1);
        try {
            validatorService.testValidate2(calendar.getTime(), new Date());
        } catch (ParaValidException e) {
            logger.error("======= testValid ======= ", e);
            exception = true;
        } finally {
            Assert.assertFalse(exception);
        }

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test()
    public void testValid1_1() {
        PersonDto dto = new PersonDto();
        thrown.expect(ParaValidException.class); //期待抛出的异常类型
        try {
            validatorService.testValidate(dto);
        } catch (Exception e) {
            logger.error("testValid:" + e.getMessage());
            throw e;
        }
    }

    @Test(expected = ParaValidException.class)
    public void testValid1_2() {
        PersonDto dto = new PersonDto();
        Car car = new Car();
        dto.setCar(car);
        try {
            validatorService.testValidate(dto);
        } catch (Exception e) {
            logger.error("testValid:" + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testValid1_3() {
        PersonDto dto = new PersonDto();
        Car car = new Car();
        Gear gear = new Gear();
        car.setBrand("宝马");
        car.setGearList(gear);
        car.setPassengers(4);
        car.setSeatCount(4);
        dto.setCar(car);

        validatorService.testValidate(dto);

        Assert.assertTrue(true);
    }

}
