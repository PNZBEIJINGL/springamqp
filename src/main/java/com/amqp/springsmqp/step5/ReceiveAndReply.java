package com.amqp.springsmqp.step5;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.ReceiveAndReplyCallback;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 示例1,
 */
public class ReceiveAndReply {
    public static void main(String[] args) {
        //CachingConnectionFactory默认为本地连接
        // ConnectionFactory connectionFactory = new CachingConnectionFactory();
        ConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        AmqpAdmin admin = new RabbitAdmin(connectionFactory);
        admin.declareQueue(new Queue("myqueue"));

        AmqpTemplate template = new RabbitTemplate(connectionFactory);
        boolean b=template.receiveAndReply("myqueue",new ReceiveAndReplyCallback<String,String>(){
            public String handle(String str) {
                System.out.println("getMessage is :" + str);
                //doSomething
                return "OK";
            }
        });
    }
}
