package com.xyula.snippets.spring.scheculer;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Laijie on 2016/7/28.
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Thread.sleep(30000);
        context.close();
    }
}
