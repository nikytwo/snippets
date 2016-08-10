package com.bsy.webservice.Impl;

import com.bsy.webservice.TrafficService;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by Laijie on 2016/8/9.
 */

@WebService
public class TrafficServiceImpl implements TrafficService {

    @WebMethod
    public String getTest(int id){
        return "Transfer to " + id;
    }

    public static void main(String[] args){
        System.out.println("Starting Server");
        TrafficServiceImpl implementor = new TrafficServiceImpl();
        String address = "http://localhost:8080/trafficService";

        // Endpoint 为 jdk6+ 自带的
        // Endpoint.publish(address, implementor);

        // cxf 提供的
        JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
        svrFactory.setServiceClass(TrafficServiceImpl.class);
        svrFactory.setAddress(address);
        svrFactory.setServiceBean(implementor);
        svrFactory.getInInterceptors().add(new LoggingInInterceptor());
        svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
        svrFactory.create();
    }
}
