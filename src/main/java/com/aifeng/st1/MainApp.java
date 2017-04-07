package com.aifeng.st1;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by pro on 17-4-6.
 */
public class MainApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("Beans1.xml");

        CustomEventPublisher cvp = (CustomEventPublisher) context.getBean("customEventPublisher");

        cvp.publish();
        cvp.publish();
    }
}