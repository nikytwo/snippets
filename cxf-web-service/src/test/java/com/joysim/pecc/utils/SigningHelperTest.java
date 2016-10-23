package com.joysim.pecc.utils;

import org.junit.Assert;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Laijie on 2016/8/6.
 *
 * �����㷨��ز���
 */
public class SigningHelperTest {
    public static final String VIOLATION_ORDER_URI = "/violation/order";
    public static final String VIOLATION_SUBMIT_URI = "/violation/submit";
    public static final String VIOLATION_QUERY_ORDER_URI = "/violation/query/order";
    public static final String VIOLATION_JUDGE_URI = "/violation/judge";
    public static final String GET_CAR_PECCANCY_URI = "/wz/getCarPeccancy/v3";

    private static String USER_NAME = "bsy";
    //Ĭ�ϵ�ַ
    private static String SERVER_URI = "http://test.haicar.cn/joysim-car/server";
    //Ĭ����Կ
    private static String SECRET_KEY = "b732b4cc0cfacb44591f878195952f52";

    @Test
    public void createMacSignature() throws InvalidKeyException, NoSuchAlgorithmException {
        String source = "{\"site\":\"�ݽ�··��\",\"drivingLicence\":\"\",\"engine\":\"D15140\",\"behavior\":\"������Υ���涨ͣ�š���ʱͣ����������������������ͨ�е�\",\"score\":\"0\",\"sequences\":\"4401267900972911\",\"violationTime\":\"2016-07-03 06:14:00\",\"type\":\"1\",\"plateType\":\"2\",\"mobilePhone\":\"\",\"fileNumber\":\"\",\"overdue\":\"0\",\"vin\":\"074686\",\"plateNumber\":\"��AS8T03\",\"fine\":\"200\",\"extNo\":\"1470472964336\"}";
//        String source = "{\"site\":\"������Υ���涨ͣ�š���ʱͣ����������������������ͨ�е�\"}";

        byte[] mac = HttpRequestSiningHelper.createMacSignature(SECRET_KEY, source);
        String base64 = HttpRequestSiningHelper.encodeBase64WithoutLinefeed(mac);

        Assert.assertEquals("34NCHUDgNQIIdZCogLR+3w==", base64);
    }

    @Test
    public void createRequestSignature() throws NoSuchAlgorithmException {
        Map<String, Object> map = new HashMap<String, Object>();
        // ����
        map.put("extNo", "1470472964336");//���뷽������
        map.put("type", 1);// 5Ϊ�۷ֵ���1Ϊ�ǿ۷ֵ�
        map.put("plateType", 2);//������
        map.put("plateNumber", "��AS8T03");//����
        map.put("vin", "074686");//����ʶ����
        map.put("engine", "D15140");//��������
        map.put("violationTime", "2016-07-03 06:14:00");//Υ��ʱ��
        map.put("behavior", "������Υ���涨ͣ�š���ʱͣ����������������������ͨ�е�");//Υ����Ϊ
        map.put("site", "�ݽ�··��");//Υ�µص�
        map.put("score", "0");//�۷�
        map.put("fine", "200");//����
        map.put("sequences", "4401267900972911");//Υ����Ż�������
        map.put("overdue", "0");//���ɽ�
        map.put("drivingLicence", "");//��ʻ֤��
        map.put("fileNumber", "");//��ʻ֤���
        map.put("mobilePhone", "");//�ֻ���

        String param = HttpPostUtils.hashMapToJson(map);
        System.out.println(param);
        String date = String.valueOf(123456789);
        String rst = HttpRequestSiningHelper.createSignatureBase("POST", date, SERVER_URI +"/violation/order", param);

        Assert.assertEquals("POST\n" +
                "123456789\n" +
                "http://test.haicar.cn/joysim-car/server/violation/order\n" +
                "5c24c516c0abbe2cf39a0bf5ae8d92b9", rst);
    }

    @Test
    public void hashMapToJson(){

        String para = "extNo";
        System.out.println(para.hashCode());

        Map<String, Object> map = new HashMap<String, Object>();
        // ����
        map.put("extNo", "1470472964336");//���뷽������
        map.put("type", 1);// 5Ϊ�۷ֵ���1Ϊ�ǿ۷ֵ�
        map.put("plateType", 2);//������
        map.put("plateNumber", "��AS8T03");//����
        map.put("vin", "074686");//����ʶ����
        map.put("engine", "D15140");//��������
        map.put("violationTime", "2016-07-03 06:14:00");//Υ��ʱ��
        map.put("behavior", "������Υ���涨ͣ�š���ʱͣ����������������������ͨ�е�");//Υ����Ϊ
        map.put("site", "�ݽ�··��");//Υ�µص�
        map.put("score", "0");//�۷�
        map.put("fine", "200");//����
        map.put("sequences", "4401267900972911");//Υ����Ż�������
        map.put("overdue", "0");//���ɽ�
        map.put("drivingLicence", "");//��ʻ֤��
        map.put("fileNumber", "");//��ʻ֤���
        map.put("mobilePhone", "");//�ֻ���

        String param = HttpPostUtils.hashMapToJson(map);
        Assert.assertEquals("{\"site\":\"�ݽ�··��\",\"drivingLicence\":\"\",\"engine\":\"D15140\",\"behavior\":\"������Υ���涨ͣ�š���ʱͣ����������������������ͨ�е�\",\"score\":\"0\",\"sequences\":\"4401267900972911\",\"violationTime\":\"2016-07-03 06:14:00\",\"type\":\"1\",\"plateType\":\"2\",\"mobilePhone\":\"\",\"fileNumber\":\"\",\"overdue\":\"0\",\"vin\":\"074686\",\"plateNumber\":\"��AS8T03\",\"fine\":\"200\",\"extNo\":\"1470472964336\"}", param);
    }

    @Test
    public void buildHeaders(){
        // java.version
        Properties props = System.getProperties();
        System.out.println("Java�����л����汾��" + props.getProperty("java.version"));
        Map<String, Object> map = new HashMap<String, Object>();
        // ����
        map.put("plateNumber", "��AS8T03");
        map.put("plateType", "2");
        map.put("vin", "074686");
        map.put("engine", "D15140");

        String param = HttpPostUtils.hashMapToJson(map);
        String uri = "http://112.74.107.195:9093/joysim-car/server/wz/getCarPeccancy/v3";

        Map<String, String> headers = new HashMap<String, String>();
        String date = "1470879673027";
        final StringBuilder builder = new StringBuilder(USER_NAME);
        builder.append(":");
        builder.append(HttpRequestSiningHelper.createRequestSignature("POST", date, uri, param, SECRET_KEY));// ����ǩ��ͷ��Ϣ
        headers.put(HmacAttributes.X_HMAC_AUTH_SIGNATURE, builder.toString());
        headers.put(HmacAttributes.X_HMAC_AUTH_METHOD, "HmacMD5");
        headers.put(HmacAttributes.X_HMAC_AUTH_DATE, date);

        Assert.assertEquals("POST\n1470879673027\n" +
                "http://112.74.107.195:9093/joysim-car/server/wz/getCarPeccancy/v3\n" +
                "68e834d5900d06bcd53be729c6219cfd",HttpRequestSiningHelper.createSignatureBase("POST", date, uri, param));

        Assert.assertEquals("{\"plateType\":\"2\",\"engine\":\"D15140\",\"vin\":\"074686\",\"plateNumber\":\"��AS8T03\"}", param);
        Assert.assertEquals("{x-hmac-auth-date=1470879673027, x-hmac-auth-signature=bsy:YsQZjLG3JaRO1eH/e28m5g==, x-hmac-signature-method=HmacMD5}", headers.toString());
    }
}
