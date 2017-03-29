package com.xyula.snippets.activemq;

import org.junit.Assert;
import org.junit.Test;

/**
 * 基本使用测试
 * Created by Lai Jie on 2017/3/28.
 */
public class MQTest {

    @Test
    public void sendNReceive() throws InterruptedException {
        String sendMsg = "aaa";
        Producer producer = new Producer();
        producer.send(sendMsg);

        sendMsg = "ccc";
        producer = new Producer();
        producer.send(sendMsg);


        sendMsg = "ddd";
        producer = new Producer();
        producer.send(sendMsg);

        System.out.println("send over");

        Thread.sleep(3000);

        Consumer consumer = new Consumer();
        String receiveMsg = consumer.receive();
        Assert.assertEquals("aaa", receiveMsg);

        Thread.sleep(3000);

        consumer = new Consumer();
        receiveMsg = consumer.receive();
        Assert.assertEquals("ccc", receiveMsg);

        Thread.sleep(3000);

        consumer = new Consumer();
        receiveMsg = consumer.receive();
        Assert.assertEquals("ddd", receiveMsg);
    }
}
