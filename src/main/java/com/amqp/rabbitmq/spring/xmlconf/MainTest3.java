package com.amqp.rabbitmq.spring.xmlconf;

import com.amqp.rabbitmq.spring.javaconf.RabbitConfiguration;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 方式3  With JAVA Configuration
 */
public class MainTest3 {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        template.convertAndSend("myqueue", "foo");
        String foo = (String) template.receiveAndConvert("myqueue");

    }
}
