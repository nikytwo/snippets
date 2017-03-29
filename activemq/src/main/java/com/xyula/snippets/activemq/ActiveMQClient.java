package com.xyula.snippets.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * activemq Listener（监听消息的消费者） 与 消息的生产者.
 * Created by Lai Jie on 2017/3/28.
 */
public class ActiveMQClient {
    private static AtomicInteger cnt = new AtomicInteger(0);
    private static final String ACTIVITY_NAME = "xyula.activity";
    private static final String DELETE_NAME = "xyula.delete";

    private final JmsTemplate jmsTemplate;

    @Autowired
    public ActiveMQClient(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * 发送消息至 "xyula.activity"
     * @param message 消息内容
     */
    public void sendActivityMessage(final String message) {
        jmsTemplate.send(ACTIVITY_NAME, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

    /**
     * 发送消息至 "xyula.delete"
     * @param message 消息内容
     */
    public void sendDeleteMessage(final String message) {
        jmsTemplate.send(DELETE_NAME, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

    /**
     * 监听 "xyula.activity" 的处理方法
     * @param message 接收到的信息
     */
    public void activity(String message) {
        System.out.println("接收到消息-activity： " + message);
    }

    /**
     * 监听 "xyula.delete" 的处理方法
     * @param message 接收到的信息
     */
    public void delete(String message) {
        System.out.println(cnt.incrementAndGet());
        Random random = new Random();
        int rd = random.nextInt(2);
        if (rd == 1) {
            System.out.println("exception");
            throw new RuntimeException("test");
        }

        System.out.println("接收到消息-delete： " + message);
    }
}
