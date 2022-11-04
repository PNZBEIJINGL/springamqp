package com.amqp.springsmqp.step5;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class SendingTest {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        AmqpAdmin admin = new RabbitAdmin(connectionFactory);
        admin.declareQueue(new Queue("myqueue"));

        AmqpTemplate template = new RabbitTemplate(connectionFactory);
        template.convertAndSend("myqueue", "Hello Word");

        System.out.println("getMessage is :" + "Hello Word");
    }
}
