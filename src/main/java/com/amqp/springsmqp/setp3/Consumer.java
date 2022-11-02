package com.amqp.springsmqp.setp3;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Consumer {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        AmqpTemplate template2 = context.getBean(AmqpTemplate.class);
        String foo = (String) template2.receiveAndConvert("myqueue");
        System.out.println("getMessage is :" + foo);
    }
}
