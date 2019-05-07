package com.amqp.rabbitmq.spring.xmlconf;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
/**
 * 方式3 With XML Configuration
 */
public class MainTest2 {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/rabbit-context.xml");
        //
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        //发送消息
        try {
            template.convertAndSend("myqueue", "foo");
            Thread.sleep(1000);// 休眠1秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //容器销毁
            ((ClassPathXmlApplicationContext) context).destroy();

        }
        String foo = (String) template.receiveAndConvert("myqueue");
    }
}
