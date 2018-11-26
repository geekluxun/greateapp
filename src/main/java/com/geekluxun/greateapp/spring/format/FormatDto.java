package com.geekluxun.greateapp.spring.format;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-23 16:34
 * @Description:
 * @Other:
 */
@Data
public class FormatDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date cueDate;
}
