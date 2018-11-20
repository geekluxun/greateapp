package com.geekluxun.greateapp.example;

import java.io.Serializable;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;

/**
 * Created by luxun on 2018/1/17.
 */
public class JSONDemo {

    /**
     * 此示例使用用于KEY是变量情况，只要获取值的情况
     * JSONObject 是一个 Map
     * JSONArray 是一个 List
     */
    private void jsonStringParse() {
        String json = "{\n" +
                "    \"data\": {\n" +
                "        \"wzOrderId\": \"2018011717430133\",\n" +
                "        \"orderTrack\": [\n" +
                "            {\n" +
                "                \"63348100837\": {\n" +
                "                    \"3985573\": [\n" +
                "                        {\n" +
                "                            \"msgTime\": \"2017-10-11 09:30:46\",\n" +
                "                            \"content\": \"您提交了订单，请等待系统确认\",\n" +
                "                            \"operator\": \"客户\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"msgTime\": \"2017-10-11 09:40:08\",\n" +
                "                            \"content\": \"尊敬的客户,您的订单将由第三方厂商为您生产,订单正在等待第三方厂商处理,请您耐心等待\",\n" +
                "                            \"operator\": \"系统\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"msgTime\": \"2017-10-11 09:50:35\",\n" +
                "                            \"content\": \"您的订单正在出库， 请耐心等待 \",\n" +
                "                            \"operator\": \"系统 \"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"msgTime\": \" \t\t\t\t\t\t\t\t\t2017 - 10 - 11 10: 40: 11 \",\n" +
                "                            \"content\": \" \t\t\t\t\t\t\t\t\t您的订单已交付申通快递， 运单号为3340649775623 \",\n" +
                "                            \"operator\": \" \t\t\t\t\t\t\t\t\t系统 \"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"msgTime\": \" \t\t\t\t\t\t\t\t\t2017 - 10 - 12 10: 59: 05 \",\n" +
                "                            \"content\": \" [西安市] 陕西西安公司 - 业务员 已收件 \",\n" +
                "                            \"operator\": \" \t\t\t\t\t\t\t\t\t申通快递 \"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"msgTime\": \" \t\t\t\t\t\t\t\t\t2017 - 10 - 14 17: 35: 16 \",\n" +
                "                            \"content\": \" [西安市] 陕西西安航空部 - 发往 - 上海中转部 \",\n" +
                "                            \"operator\": \" \t\t\t\t\t\t\t\t\t申通快递 \"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"msgTime\": \" \t\t\t\t\t\t\t\t\t2017 - 10 - 15 04: 26: 15 \",\n" +
                "                            \"content\": \" [西安市] 陕西西安航空部 - 发往 - 上海中转部 \",\n" +
                "                            \"operator\": \" \t\t\t\t\t\t\t\t\t申通快递 \"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"msgTime\": \" \t\t\t\t\t\t\t\t\t2017 - 10 - 16 02: 50: 25 \",\n" +
                "                            \"content\": \" [上海市] 上海中转部 - 发往 - 上海宝山公司 \",\n" +
                "                            \"operator\": \" \t\t\t\t\t\t\t\t\t申通快递 \"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"msgTime\": \" \t\t\t\t\t\t\t\t\t2017 - 10 - 16 02: 50: 49 \",\n" +
                "                            \"content\": \" [上海市] 快件 已到达 - 上海宝山公司 \",\n" +
                "                            \"operator\": \" \t\t\t\t\t\t\t\t\t申通快递 \"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"msgTime\": \" \t\t\t\t\t\t\t\t\t2017 - 10 - 18 11: 27: 38 \",\n" +
                "                            \"content\": \" [上海市] 快件 已到达 - 上海宝山公司 \",\n" +
                "                            \"operator\": \" \t\t\t\t\t\t\t\t\t申通快递 \"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"msgTime\": \" \t\t\t\t\t\t\t\t\t2017 - 10 - 18 12: 12: 38 \",\n" +
                "                            \"content\": \" [上海市] 上海宝山公司 - 的派件 \t\t\t\t\t\t\t\t\t员 - 谢坤(18917011160) - 正在派件 \",\n" +
                "                            \"operator\": \" \t\t\t\t\t\t\t\t\t申通快递 \"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"msgTime\": \" \t\t\t\t\t\t\t\t\t2017 - 10 - 18 19: 19: 42 \",\n" +
                "                            \"content\": \" [上海市] 草签 已签收 \",\n" +
                "                            \"operator\": \" \t\t\t\t\t\t\t\t\t申通快递 \"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"63350934466\": {\n" +
                "                    \"180389 \": [\n" +
                "                        {\n" +
                "                            \"msgTime\": \" \t\t\t\t\t\t\t\t\t\t2017 - 10 - 11 09: 30: 46 \",\n" +
                "                            \"content\": \" \t\t\t\t\t\t\t\t\t\t您提交了订单， 请等待系统确认 \",\n" +
                "                            \"operator\": \" \t\t\t\t\t\t\t\t\t\t客户 \"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"detail\": \" \t\t\t\t\t\t\t\t\t\tsuccess \",\n" +
                "    \"result\": \" \t\t\t\t\t\t\t\t\t\t0 \"\n" +
                "}";

        JSONObject object = JSON.parseObject(json);

        System.out.println("object:" + object);

        JSONObject data = (JSONObject) object.get("data");

        System.out.println("data:" + data);

        JSONArray orderTrack = (JSONArray) data.get("orderTrack");

        System.out.println("orderTrack:" + orderTrack);

        List<DD> list = new ArrayList<>();

        /** 遍历JSONArray*/
        for (Object d : orderTrack) {
            Map<String, Object> itemMap = (Map<String, Object>) d;//JSONObject.toJavaObject((JSONObject) d, Map.class);

            for (Map.Entry<String, Object> entry2 : (itemMap).entrySet()) {
                /** entry2 的 value 也是一个 map, 遍历这个map*/
                for (Map.Entry<String, Object> entry3 : ((Map<String, Object>) entry2.getValue()).entrySet()) {

                    System.out.println("=====value3:=====" + entry3.getValue());

                    //JSONArray array = (JSONArray) entry3.getValue();
                    List array = (List) entry3.getValue();

                    /** entry3 的 value 是一个 list, 遍历这个list*/
                    for (Object ob : array) {
                        DD v = new DD();
                        v.setContent((String) ((Map) ob).get("content"));
                        v.setMsgTime((String) ((Map) ob).get("msgTime"));
                        v.setOperator((String) ((Map) ob).get("operator"));
                        list.add(v);
                    }
                }
            }
        }

        System.out.println("list:" + list);
    }

    public static void main(String[] argc) {
        JSONDemo demo = new JSONDemo();

        demo.jsonStringParse();
        demo.test1();
    }

    private void test1() {
        /** json本质上是一个键值对，key是Stirng ,value取值：
         string
         number
         object
         array
         true
         false
         null*/
        /**
         * {
         "canPigsFly": null,     // null
         "areWeThereYet": false, // boolean
         "answerToLife": 42,     // number
         "name": "Bart",         // string
         "moreData": {},         // object
         "things": []            // array
         }
         */


        /** 此json串经过压缩转义处理过*/
        String jsonStr = "{\"name\":\"str\",\"age\":0,\"para\":null,\"bankCard\":[],\"born\":\"2017-01-03 13:36:46\"}";

        UserBean object = JSON.parseObject(jsonStr, UserBean.class);

        System.out.println("object" + object);

        UserBean userBean = new UserBean();
        userBean.setNoSerial("nono");
        userBean.setName("luxun");
        userBean.setBornDate(new Date());
        userBean.setSex(SexEnum.WORMAN);

        /** 输出值为null的字段*/


        String serial = JSON.toJSONString(userBean,
                SerializerFeature.WRITE_MAP_NULL_FEATURES, /** 空值输出null*/
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteEnumUsingToString  /** 枚举值使用字符串而不是ordinal表示*/
        );

        System.out.println("序列化结果:" + serial);

    }

    private class DD {
        private String msgTime;
        private String content;
        private String operator;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getMsgTime() {
            return msgTime;
        }

        public void setMsgTime(String msgTime) {
            this.msgTime = msgTime;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }
    }
}


class UserBean implements Serializable {
    private String name;
    private Integer age;
    private Map para;
    private List bankCard;
    /**
     * 格式化时间很有用处！！！ name 从 born 转换成 实际的bornDate字段
     */
    @JSONField(name = "born", format = "yyyy-MM-dd HH:mm:ss")
    private Date bornDate;

    private Boolean isBoy;

    /**
     * 此字段不会被序列化
     */
    @JSONField(serialize = false)
    private String noSerial;


    private SexEnum sex;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List getBankCard() {
        return bankCard;
    }

    public void setBankCard(List bankCard) {
        this.bankCard = bankCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map getPara() {
        return para;
    }

    public void setPara(Map para) {
        this.para = para;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }


    public String getNoSerial() {
        return noSerial;
    }

    public void setNoSerial(String noSerial) {
        this.noSerial = noSerial;
    }


    public Boolean getBoy() {
        return isBoy;
    }

    public void setBoy(Boolean boy) {
        isBoy = boy;
    }


    public SexEnum getSex() {
        return sex;
    }


    public void setSex(SexEnum sex) {
        this.sex = sex;
    }


}


enum SexEnum {
    MAN, WORMAN
}


