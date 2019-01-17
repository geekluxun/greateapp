package com.geekluxun.messagemiddleware.rabbitmq.transaction.dto;

import lombok.Data;

import java.awt.*;
import java.io.Serializable;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-17 14:15
 * @Description:
 * @Other:
 */
@Data
public class UserInfoDto implements Serializable {
    private String userId;
    private String name;
}
