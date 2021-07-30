package com.amqp.rabbitmq.publishsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //fanout表示分发,Fanout交换器，所有的消费者得到同样的队列信息
        channel.exchangeDeclare("logs", "fanout");

        //分发信息
        for (int i = 0; i < 5; i++) {
            String message = i + ". message is Hello world";
            channel.basicPublish("logs", "", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }


        channel.close();
        connection.close();
    }
}
