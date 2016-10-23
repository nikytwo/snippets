package com.bsy.webservice;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.bsy.webservice.Impl.TrafficViolateImpl;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.Endpoint;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Laijie on 2016/8/12.
 *
 * 启动类
 */
public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    @Parameter(names = {"--host", "-h"}, description = "http host")
    private String host = "localhost";
    @Parameter(names = {"--port", "-p"}, description = "http port")
    private int port = 8080;
    @Parameter(names = {"--context-path", "-c"}, description = "http context path")
    private String path = "/TrafficViolate";

    public static void main(String[] args) {
        try {
            System.out.println("Starting Server");
            TrafficViolateImpl implementor = TrafficViolateImpl.instance();
            // 读取参数中指定的端口和上下文路径
            App app = new App();
            new JCommander(app, args);
            URL url = new URL("http", app.host, app.port, app.path);

            // Endpoint 为 jdk6+ 自带的
//             Endpoint.publish(url.toString(), implementor);

            // cxf 提供的
            JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
            svrFactory.setServiceClass(TrafficViolateImpl.class);
            svrFactory.setAddress(url.toString());
            svrFactory.setServiceBean(implementor);
            svrFactory.getInInterceptors().add(new LoggingInInterceptor());
            svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
            svrFactory.create();
        } catch (Exception e){
            logger.error("程序执行出错", e);
        }
    }
}
