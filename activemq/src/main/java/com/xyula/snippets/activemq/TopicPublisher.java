package com.xyula.snippets.activemq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 消息发布者
 * Created by Lai Jie on 2017/3/29.
 */
public class TopicPublisher {

    private static final String TOPIC_NAME = "xyula.topic";

    @Resource
    private JmsTemplate jmsTopicTemplate;


    public void send(final String msg) {
        jmsTopicTemplate.send(TOPIC_NAME, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

}
