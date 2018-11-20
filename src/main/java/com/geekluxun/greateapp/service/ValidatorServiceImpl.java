package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.annotation.ParaValidator;
import com.geekluxun.greateapp.dto.PersonDto;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by luxun on 2017/11/14.
 */
@Service("validatorService")
@ParaValidator
public class ValidatorServiceImpl extends BaseService implements ValidatorService {

    @Override
    public void testValidate(PersonDto dto) {
        logger.info("================ testValidate ================:");
    }

    @Override
    public void testValidate2(Date date1, Date date2) {
        logger.info("================ testValidate2 ================:", date1);
    }

}

