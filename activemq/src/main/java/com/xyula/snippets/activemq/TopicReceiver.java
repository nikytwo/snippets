package com.xyula.snippets.activemq;

import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消息订阅者
 * Created by Lai Jie on 2017/3/29.
 */
public class TopicReceiver implements MessageListener {
    private static AtomicInteger cnt = new AtomicInteger(0);

    private final String name;

    @Autowired
    public TopicReceiver(String name) {
        this.name = name;
    }

    public void onMessage(Message message) {
        System.out.println(cnt.incrementAndGet());
        Random random = new Random();
        int cnt = random.nextInt(2);
        if (cnt == 1) {
            System.out.println("exception");
            throw new RuntimeException("test");
        }
        if (message instanceof TextMessage) {
            try {
                TextMessage msg = (TextMessage) message;
                System.out.println(name + "-接收到消息：" + msg.getText() + "\n");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(message);
        }
    }
}
