package com.amqp.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicSender {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");


        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明一个匹配模式的交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        //待发送的消息
        String[] routingKeys = new String[]{
                "customer.error",
                "product.info",
                "product.error",
                "product.error.log",
                "accpet.info.log",
        };
        //发送消息
        for (String routingKey : routingKeys) {
            String message = "routingKey=" + routingKey + " log:" + " this is information";
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
            System.out.println("TopicSend Sent '" + routingKey + "':'" + message + "'");
        }


        channel.close();
        connection.close();

    }
}
