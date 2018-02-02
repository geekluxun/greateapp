/**
 *
 */
package com.geekluxun.greateapp.spring.batch;

import com.geekluxun.greateapp.entity.TUser;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;


/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/02/02 13:50
 * Description:  要批处理的数据映射成POJO,此处是从一个文本文件读取的数据
 */
public class UserMapper implements FieldSetMapper<TUser> {
    public TUser mapFieldSet(FieldSet fs) throws BindException {
        TUser u = new TUser();
        u.setId(fs.readLong(0));
        u.setName(fs.readString(1));
        u.setPassword(fs.readString(2));
        u.setCreateTime(fs.readDate(3));
        u.setModifyTime(fs.readDate(4));
        u.setRemained(fs.readInt(5));
        u.setVersion(fs.readInt(6));

        return u;
    }
}
