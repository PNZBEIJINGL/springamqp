package com.amqp.rabbitmq.spring.javaconf;

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
        template.convertAndSend("myqueue", "this is a message for MainTest3 [JAVA Configuration Testing]");
        template.convertAndSend("*.message","test.message");
        //String foo = (String) template.receiveAndConvert("myqueue");

    }
}
