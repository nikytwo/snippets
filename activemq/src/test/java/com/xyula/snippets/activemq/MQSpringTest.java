package com.xyula.snippets.activemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 整合 spring 的测试
 * Created by Lai Jie on 2017/3/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mq.xml"})
public class MQSpringTest {

    @Resource
    private ActiveMQClient activemqClient;
    @Resource
    private TopicPublisher publisher;

    @Test
    public void test() throws InterruptedException {
        activemqClient.sendActivityMessage("aaa");
        Thread.sleep(3000);

        activemqClient.sendDeleteMessage("bbb");
        Thread.sleep(3000);

        activemqClient.sendDeleteMessage("aaa");
        Thread.sleep(3000);

        activemqClient.sendActivityMessage("ccc");

        // 给予足够时间等待消息接收
        Thread.sleep(10000);
    }

    @Test
    public void topic() throws InterruptedException {
        publisher.send("aaa");
        Thread.sleep(3000);

        publisher.send("bbb");
        Thread.sleep(3000);

        publisher.send("ccc");

        // 给予足够时间等待消息接收
        Thread.sleep(10000);
    }
}
