/***********************************************************************
 *
 * û������
 *
 * batch_sample 1.0Դ���뿽��Ȩ�������Ĵ�ʱ������������޹�˾���У�
 * �ܵ����ɵı������κι�˾����ˣ�δ����Ȩ�������Կ�����
 *
 * @copyright Copyright: 2002-2006 Beijing Startimes
 *              Software Technology Co. Ltd.
 * @creator liugr liugr@startimes.com.cn <br/>
 * @create-time ����12:28:03
 * @revision $Id$
 *
 ***********************************************************************/
package com.geekluxun.greateapp.spring.batch;

import com.alibaba.fastjson.JSON;
import org.springframework.batch.item.ItemWriter;

import java.util.List;


/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/02/02 13:50
 * Description:  批处理后的数据写，此处只是简单的打印，可以把处理后的数据写入消息队列或者数据库，文件等
 */
public class MessagesItemWriter implements ItemWriter<Message> {

    public void write(List<? extends Message> messages) throws Exception {
        System.out.println("=========write results=========");
        for (Message m : messages) {
            System.out.println(JSON.toJSONString(m.getUser()));
        }
    }
}
