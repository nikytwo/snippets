package com.bsy.webservice.Impl;

import com.bsy.webservice.TrafficViolate;
import com.joysim.pecc.utils.HmacAttributes;
import com.joysim.pecc.utils.HttpPostUtils;
import com.joysim.pecc.utils.HttpRequestSiningHelper;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Laijie on 2016/8/9.
 *
 * 车辆违章查询代理服务
 */
@WebService
public class TrafficViolateImpl implements TrafficViolate {
    private static final Logger logger = LoggerFactory.getLogger(TrafficViolateImpl.class);
    public static final String VIOLATION_ORDER_URI = "/violation/order";
    public static final String VIOLATION_SUBMIT_URI = "/violation/submit";
    public static final String VIOLATION_QUERY_ORDER_URI = "/violation/query/order";
    public static final String VIOLATION_JUDGE_URI = "/violation/judge";
    public static final String GET_CAR_PECCANCY_URI = "/wz/getCarPeccancy/v3";
    public static final String WEB_SERVICE_ERROR = "{\"result\":-1,\"message\":\"web service 代理失败\" }";
    public static final String ERROR_MESSAGE = "调用 web service 代理失败";

    private static String USER_NAME = "bsy";
    //默认地址
    private static String SERVER_URI = "http://test.haicar.cn/joysim-car/server";
    //默认密钥
    private static String SECRET_KEY = "b732b4cc0cfacb44591f878195952f52";

    static {
        // 读取配置
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File("api-server.properties"));
            SERVER_URI = config.getString("haicar.api.baseUri", SERVER_URI);
            USER_NAME = config.getString("haicar.api.username", USER_NAME);
            SECRET_KEY = config.getString("haicar.api.secretKey", SECRET_KEY);
            logger.info("加载配置文件成功...");
        } catch (ConfigurationException e) {
            logger.error("加载配置文件失败,异常信息", e);
        }
    }

    @WebMethod
    public String order(String extNo, int type, int plateType, String plateNo, String vin, String engineNo,
                        String violationTime, String behavior, String site, String score, String fine,
                        String sequences, String overdue, String drivingLicence, String fileNo, String phone) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            // 参数
            map.put("extNo", extNo);
            map.put("type", type);
            map.put("plateType", plateType);
            map.put("plateNumber", plateNo);
            map.put("vin", vin);
            map.put("engine", engineNo);
            map.put("violationTime", violationTime);
            map.put("behavior", behavior);
            map.put("site", site);
            map.put("score", score);
            map.put("fine", fine);
            map.put("sequences", sequences);
            map.put("overdue", overdue);
            map.put("drivingLicence", drivingLicence);
            map.put("fileNumber", fileNo);
            map.put("mobilePhone", phone);

            String param = HttpPostUtils.hashMapToJson(map);
            String uri = SERVER_URI + VIOLATION_ORDER_URI;

            // 生成签名头信息
            Map<String, String> headers = buildHeaders(param, uri);

            return HttpPostUtils.http(uri, param.getBytes("UTF-8"), headers);
        } catch (IOException e) {
            logger.error(ERROR_MESSAGE, e);

            return WEB_SERVICE_ERROR;
        }
    }

    @WebMethod
    public String Submit(String extNo) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("extNo", extNo);
            String param = HttpPostUtils.hashMapToJson(map);
            String uri = SERVER_URI + VIOLATION_SUBMIT_URI;

            // 生成签名头信息
            Map<String, String> headers = buildHeaders(param, uri);

            return HttpPostUtils.http(uri, param.getBytes("UTF-8"), headers);
        } catch (IOException e) {
            logger.error(ERROR_MESSAGE, e);

            return WEB_SERVICE_ERROR;
        }
    }

    @WebMethod
    public String QueryOrder(String extNo) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            // 参数
            map.put("extNo", extNo);
            String param = HttpPostUtils.hashMapToJson(map);
            String uri = SERVER_URI + VIOLATION_QUERY_ORDER_URI;

            // 生成签名头信息
            Map<String, String> headers = buildHeaders(param, uri);

            return HttpPostUtils.http(uri, param.getBytes("UTF-8"), headers);
        } catch (IOException e) {
            logger.error(ERROR_MESSAGE, e);

            return WEB_SERVICE_ERROR;
        }
    }

    @WebMethod
    public String Judge(String cityId, String behaviorCode) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            // 参数
            map.put("cityId", cityId);
            map.put("behaviorCode", behaviorCode);
            String param = HttpPostUtils.hashMapToJson(map);
            String uri = SERVER_URI + VIOLATION_JUDGE_URI;

            // 生成签名头信息
            Map<String, String> headers = buildHeaders(param, uri);

            return HttpPostUtils.http(uri, param.getBytes("UTF-8"), headers);
        } catch (IOException e) {
            logger.error(ERROR_MESSAGE, e);

            return WEB_SERVICE_ERROR;
        }
    }

    @WebMethod
    public String Pecc(String plateNumber, String plateType, String vin, String engineNo) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            // 参数
            map.put("plateNumber", plateNumber);
            map.put("plateType", plateType);
            map.put("vin", vin);
            map.put("engine", engineNo);

            String param = HttpPostUtils.hashMapToJson(map);
            String uri = SERVER_URI + GET_CAR_PECCANCY_URI;

            // 生成签名头信息
            Map<String, String> headers = buildHeaders(param, uri);

            return HttpPostUtils.http(uri, param.getBytes("UTF-8"), headers);
        } catch (IOException e) {
            logger.error(ERROR_MESSAGE, e);

            return WEB_SERVICE_ERROR;
        }
    }

    private Map<String, String> buildHeaders(String param, String uri) {
        Map<String, String> headers = new HashMap<String, String>();
        String date = String.valueOf(new Date().getTime());
        final StringBuilder builder = new StringBuilder(USER_NAME);
        builder.append(":");
        builder.append(HttpRequestSiningHelper.createRequestSignature("POST", date, uri, param, SECRET_KEY));// 生成签名头信息
        headers.put(HmacAttributes.X_HMAC_AUTH_SIGNATURE, builder.toString());
        headers.put(HmacAttributes.X_HMAC_AUTH_METHOD, "HmacMD5");
        headers.put(HmacAttributes.X_HMAC_AUTH_DATE, date);
        return headers;
    }

    public static void main(String[] args) {
        System.out.println("Starting Server");
        TrafficViolateImpl implementor = new TrafficViolateImpl();
        // todo 可配置端口和路径
        String address = "http://localhost:8080/trafficService";

        // Endpoint 为 jdk6+ 自带的
        // Endpoint.publish(address, implementor);

        // cxf 提供的
        JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
        svrFactory.setServiceClass(TrafficViolateImpl.class);
        svrFactory.setAddress(address);
        svrFactory.setServiceBean(implementor);
        svrFactory.getInInterceptors().add(new LoggingInInterceptor());
        svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
        svrFactory.create();
    }
}
