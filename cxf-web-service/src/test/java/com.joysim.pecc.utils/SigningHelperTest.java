package com.joysim.pecc.utils;

import org.junit.Assert;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Laijie on 2016/8/6.
 *
 * 加密算法相关测试
 */
public class SigningHelperTest {

    public static final String key = "d4203b6ce47ee616a1ee627a42cd14dd";
    private static String serverUri = "http://112.74.107.195:9093/joysim-car/server";

    @Test
    public void createMacSignature() throws InvalidKeyException, NoSuchAlgorithmException {
        String source = "{\"site\":\"捷进路路段\",\"drivingLicence\":\"\",\"engine\":\"D15140\",\"behavior\":\"机动车违反规定停放、临时停车，妨碍其它车辆、行人通行的\",\"score\":\"0\",\"sequences\":\"4401267900972911\",\"violationTime\":\"2016-07-03 06:14:00\",\"type\":\"1\",\"plateType\":\"2\",\"mobilePhone\":\"\",\"fileNumber\":\"\",\"overdue\":\"0\",\"vin\":\"074686\",\"plateNumber\":\"粤AS8T03\",\"fine\":\"200\",\"extNo\":\"1470472964336\"}";
//        String source = "{\"site\":\"机动车违反规定停放、临时停车，妨碍其它车辆、行人通行的\"}";

        byte[] mac = HttpRequestSiningHelper.createMacSignature(key, source);
        String base64 = HttpRequestSiningHelper.encodeBase64WithoutLinefeed(mac);

        Assert.assertEquals("", base64);
    }

    @Test
    public void createRequestSignature() throws NoSuchAlgorithmException {
        Map<String, Object> map = new HashMap<String, Object>();
        // 参数
        map.put("extNo", "1470472964336");//接入方订单号
        map.put("type", 1);// 5为扣分单，1为非扣分单
        map.put("plateType", 2);//车类型
        map.put("plateNumber", "粤AS8T03");//车牌
        map.put("vin", "074686");//车辆识别码
        map.put("engine", "D15140");//发动机号
        map.put("violationTime", "2016-07-03 06:14:00");//违章时间
        map.put("behavior", "机动车违反规定停放、临时停车，妨碍其它车辆、行人通行的");//违章行为
        map.put("site", "捷进路路段");//违章地点
        map.put("score", "0");//扣分
        map.put("fine", "200");//罚款
        map.put("sequences", "4401267900972911");//违章序号或决定书号
        map.put("overdue", "0");//滞纳金
        map.put("drivingLicence", "");//驾驶证号
        map.put("fileNumber", "");//驾驶证编号
        map.put("mobilePhone", "");//手机号

        String param = HttpPostUtils.hashMapToJson(map);
        System.out.println(param);
        String date = String.valueOf(123456789);
        String rst = HttpRequestSiningHelper.createSignatureBase("POST", date, serverUri+"/violation/order", param);

        Assert.assertEquals("POST\n" +
                "123456789\n" +
                "http://112.74.107.195:9093/joysim-car/server/violation/order\n" +
                "5c24c516c0abbe2cf39a0bf5ae8d92b9", rst);
    }

    @Test
    public void hashMapToJson(){

        String para = "extNo";
        System.out.println(para.hashCode());

        Map<String, Object> map = new HashMap<String, Object>();
        // 参数
        map.put("extNo", "1470472964336");//接入方订单号
        map.put("type", 1);// 5为扣分单，1为非扣分单
        map.put("plateType", 2);//车类型
        map.put("plateNumber", "粤AS8T03");//车牌
        map.put("vin", "074686");//车辆识别码
        map.put("engine", "D15140");//发动机号
        map.put("violationTime", "2016-07-03 06:14:00");//违章时间
        map.put("behavior", "机动车违反规定停放、临时停车，妨碍其它车辆、行人通行的");//违章行为
        map.put("site", "捷进路路段");//违章地点
        map.put("score", "0");//扣分
        map.put("fine", "200");//罚款
        map.put("sequences", "4401267900972911");//违章序号或决定书号
        map.put("overdue", "0");//滞纳金
        map.put("drivingLicence", "");//驾驶证号
        map.put("fileNumber", "");//驾驶证编号
        map.put("mobilePhone", "");//手机号

        String param = HttpPostUtils.hashMapToJson(map);
        Assert.assertEquals("{\"site\":\"捷进路路段\",\"drivingLicence\":\"\",\"engine\":\"D15140\",\"behavior\":\"机动车违反规定停放、临时停车，妨碍其它车辆、行人通行的\",\"score\":\"0\",\"sequences\":\"4401267900972911\",\"violationTime\":\"2016-07-03 06:14:00\",\"type\":\"1\",\"plateType\":\"2\",\"mobilePhone\":\"\",\"fileNumber\":\"\",\"overdue\":\"0\",\"vin\":\"074686\",\"plateNumber\":\"粤AS8T03\",\"fine\":\"200\",\"extNo\":\"1470472964336\"}", param);
    }
}
