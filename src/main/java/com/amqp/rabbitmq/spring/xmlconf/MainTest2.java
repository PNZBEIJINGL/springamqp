package com.amqp.rabbitmq.spring.xmlconf;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
/**
 * 方式3 With XML Configuration
 * 修改pom.xml: spring 整合依赖 2.1.5当前最新版本  官网2.5.1需要5.4.0 以上的amqp-clent包
 */
public class MainTest2 {

    public static void main(String[] args) {
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/main/resource/rabbit-context.xml");
        //
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        //发送消息
        try {
            template.convertAndSend("myqueue", "this is a messge 4 MainTest2 [xml configuation testing]");
            Thread.sleep(1000);// 休眠1秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //容器销毁
            ((FileSystemXmlApplicationContext) context).destroy();

        }
        //String foo = (String) template.receiveAndConvert("myqueue");
    }
}
