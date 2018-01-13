package com.geekluxun.greateapp.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

/**
 * Created by luxun on 2017/11/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {
    Logger logger = LoggerFactory.getLogger(LoggerTest.class);


    @Test
    public void testLogger(){
        Integer a = null;
        Double b = 1.23;
        Map c = new HashMap();
        c.put("name", "luxun");
        c.put("age", 10);

        List d = new ArrayList();
        d.add(c);

        Date e = new Date();

        logger.info("========= 带参数 ========={}，{}，{},{},{}",a,b,c,d,e);

        TestBean bean = new TestBean();
        bean.setA(null);
        bean.setB(new Date());
        bean.setC(11);
        bean.setD(d);
        bean.setE(c);



        logger.info("=========== Bean =============== {},{}", bean,  bean.getA());


    }



    private class TestBean implements Serializable{
        @NotNull(message = "a不能为空！")
        private String a;
        private Date b;
        private Integer c;
        private List d;
        private Map e;


        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public Date getB() {
            return b;
        }

        public void setB(Date b) {
            this.b = b;
        }

        public Integer getC() {
            return c;
        }

        public void setC(Integer c) {
            this.c = c;
        }

        public List getD() {
            return d;
        }

        public void setD(List d) {
            this.d = d;
        }

        public Map getE() {
            return e;
        }

        public void setE(Map e) {
            this.e = e;
        }

        /**
         * 打印javabean
         * @return
         */
        @Override
        public String toString() {
            return  ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        }
    }
}


