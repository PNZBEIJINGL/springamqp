package com.amqp.spring.v247.setp3;

import com.amqp.spring.v247.setp3.RabbitConfiguration;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CustomerTest {

    public static void main(String[] args){
        ApplicationContext context =
                new AnnotationConfigApplicationContext(RabbitConfiguration.class);

        AmqpTemplate template2 = context.getBean(AmqpTemplate.class);
        String foo = (String) template2.receiveAndConvert("myqueue");
        System.out.println("getMessage is :" + foo);

    }
}
