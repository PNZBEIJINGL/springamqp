package com.amqp.rabbitmq.spring.connection;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
/**
 * 方式1 使用ConnectionFactory
 */
public class MainTest1 {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        AmqpAdmin admin = new RabbitAdmin(connectionFactory);
        admin.declareQueue(new Queue("myqueue"));
        AmqpTemplate template = new RabbitTemplate(connectionFactory);
        String message="this is a message 4 ConnectionFactory testing";
        template.convertAndSend("myqueue", message);
        System.out.println("message:"+message);
    }
}
