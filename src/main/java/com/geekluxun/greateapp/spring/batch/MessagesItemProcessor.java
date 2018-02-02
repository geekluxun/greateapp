/**
 *
 */
package com.geekluxun.greateapp.spring.batch;

import com.geekluxun.greateapp.entity.TUser;
import org.springframework.batch.item.ItemProcessor;

import java.util.Random;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/02/02 13:50
 * Description:  批处理
 */
public class MessagesItemProcessor implements ItemProcessor<TUser, Message> {

    public Message process(TUser user) throws Exception {
        Message m = new Message();
        m.setUser(user);
        m.setId(new Random().nextLong());
        return m;
    }

}
