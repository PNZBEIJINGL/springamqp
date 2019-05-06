package com.amqp.rabbitmq.spring.javaconf;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
/**
 * 方式3 With XML Configuration
 */
public class MainTest2 {

    public static void main(String[] args) {
        //With XML Configuration
        ApplicationContext context =
                new GenericXmlApplicationContext("classpath:/rabbit-context.xml");
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        template.convertAndSend("myqueue", "foo");
        String foo = (String) template.receiveAndConvert("myqueue");
    }
}
