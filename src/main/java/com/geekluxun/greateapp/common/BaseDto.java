package com.geekluxun.greateapp.common;



import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by luxun on 2017/11/9.
 */
public class BaseDto implements Serializable{

    @Override
    public String toString() {
        return   ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
