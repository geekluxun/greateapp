package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.annotation.ConsistentDateParameters;
import com.geekluxun.greateapp.dto.PersonDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by luxun on 2017/11/14.
 */
public interface ValidatorService {


    void testValidate(@NotNull @Valid PersonDto user);

    @ConsistentDateParameters
    void testValidate2(@NotNull Date date1, Date date2);
}
