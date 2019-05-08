package com.amqp.rabbitmq.spring.javaconf;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestCustomer {

    public static void main(String[] args){
        ApplicationContext context =
                new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        //String foo = (String) template.receiveAndConvert("myqueue");
        //System.out.println(foo);
        String foo = (String) template.receiveAndConvert("myqueue");
        System.out.println(foo);
    }
}
