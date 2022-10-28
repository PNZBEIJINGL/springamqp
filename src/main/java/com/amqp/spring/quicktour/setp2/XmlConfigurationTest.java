package com.amqp.spring.quicktour.setp2;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 示例2 以下示例与前面的示例相同，但将资源配置外部化为XM
 */
public class XmlConfigurationTest {

    public static void main(String[] args) {
        ApplicationContext context =
                new GenericXmlApplicationContext("classpath:/rabbit-amqp.xml");
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        template.convertAndSend("myqueue", "With XML Configuration testing");

        AmqpTemplate template2 = context.getBean(AmqpTemplate.class);
        String foo = (String) template2.receiveAndConvert("myqueue");
        System.out.println("getMessage is :" + foo);
    }
}
