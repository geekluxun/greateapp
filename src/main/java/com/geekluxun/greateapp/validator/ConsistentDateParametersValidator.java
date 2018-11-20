package com.geekluxun.greateapp.validator;

import com.geekluxun.greateapp.annotation.ConsistentDateParameters;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.util.Date;

/**
 * Created by luxun on 2017/11/14.
 */
//交叉参数校验必须加上此注解 主要用于对方法多个参数同时进行校验场景
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ConsistentDateParametersValidator implements ConstraintValidator<ConsistentDateParameters, Object[]> {

    @Override
    public void initialize(ConsistentDateParameters constraintAnnotation) {
    }

    /**
     * 校验方法有两个日期参数，且前一个日期在后一个日期之前
     *
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        if (value.length != 2) {
            throw new IllegalArgumentException("Illegal method signature");
        }

        //leave null-checking to @NotNull on individual parameters
        if (value[0] == null || value[1] == null) {
            return true;
        }

        if (!(value[0] instanceof Date) || !(value[1] instanceof Date)) {
            throw new IllegalArgumentException("Illegal method signature, expected two " + "parameters of type Date.");
        }

        return ((Date) value[0]).before((Date) value[1]);
    }
}
