package com.geekluxun.greateapp.spring.springmvc;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-26 10:41
 * @Description:
 * @Other:
 */
@Data
public class Pet {
    @NotEmpty
    String name;
    String color;
    Integer age;
}
