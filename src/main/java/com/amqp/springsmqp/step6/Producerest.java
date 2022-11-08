package com.amqp.springsmqp.step6;

import com.amqp.springsmqp.setp3.RabbitConfiguration;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Producerest {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        template.convertAndSend("myqueue", "Message from "+Producerest);
        System.out.println("after send ");
    }
}
