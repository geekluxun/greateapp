package com.geekluxun.greateapp.validator;

import com.geekluxun.greateapp.annotation.ValidPassengerCount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by luxun on 2017/11/14.
 */
public class ValidPassengerCountValidator implements ConstraintValidator<ValidPassengerCount, Car> {

    @Override
    public void initialize(ValidPassengerCount constraintAnnotation) {

    }

    @Override
    public boolean isValid(Car value, ConstraintValidatorContext context) {
        if ( value == null ) {
            return true;
        }
        //乘客人数<=座位数
        return value.getPassengers() <= value.getSeatCount();
    }
}
