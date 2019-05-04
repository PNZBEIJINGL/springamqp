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
        factory.setHost("localhost");


        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明一个匹配模式的交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        //待发送的消息
        String[] routingKeys = new String[]{
                "quick.orange.rabbit",
                "lazy.orange.elephant",
                "quick.orange.fox",
                "lazy.brown.fox",
                "quick.brown.fox",
                "quick.orange.male.rabbit",
                "lazy.orange.male.rabbit"
        };
        //发送消息
        for (String severity : routingKeys) {
            String message = "From " + severity + " routingKey' s message!";
            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
            System.out.println("TopicSend Sent '" + severity + "':'" + message + "'");
        }


        channel.close();
        connection.close();

    }
}
