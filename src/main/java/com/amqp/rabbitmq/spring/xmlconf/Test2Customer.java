package com.amqp.rabbitmq.spring.xmlconf;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test2Customer {

    public static void main(String[] args){
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/main/resource/rabbit-context.xml");
        //  resources右击，选择mark dirctory as test resources root
        // ApplicationContext context =new FileSystemXmlApplicationContext("classpath:/rabbit-context.xml");

        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        String foo = (String) template.receiveAndConvert("myqueue");
        System.out.println(foo);
    }
}
