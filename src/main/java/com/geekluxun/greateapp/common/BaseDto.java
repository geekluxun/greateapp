package com.geekluxun.greateapp.common;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by luxun on 2017/11/9.
 */
public class BaseDto implements Serializable{

    @Override
    public String toString() {
        return   ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
