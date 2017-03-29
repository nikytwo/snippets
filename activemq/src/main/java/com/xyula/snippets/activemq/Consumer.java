package com.xyula.snippets.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消费者
 * Created by Lai Jie on 2017/3/28.
 */
public class Consumer {

    public String receive() {
        // 创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        try {
            // 创建JMS连接实例，并启动连接
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // 创建Session对象，不开启事务
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 创建目标，可以是 Queue 或 Topic
            Destination destination = session.createQueue("xyula.watch");

            // 创建消费者
            MessageConsumer consumer = session.createConsumer(destination);

            // 获取消息
            Message message = consumer.receive();

            // 关闭会话和连接
            consumer.close();
            session.close();
            connection.close();

            return ((TextMessage)message).getText();
        } catch (Exception ex) {
            return "";
        }

    }
}
