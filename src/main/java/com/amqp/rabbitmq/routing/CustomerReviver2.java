package com.amqp.rabbitmq.routing;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class CustomerReviver2 {
    private static final String EXCHANGE_NAME = "server_logs1";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");


        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明交换机并获取匿名队列
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        //System.out.println("CustomerReviver2 queueName=" + queueName);

        List<String> keys = Arrays.asList("ERROR");
        for (String key : keys) {
            channel.queueBind(queueName, EXCHANGE_NAME, key);
            System.out.println("CustomerReviver2 exchange:" + EXCHANGE_NAME + "," +
                    " queue:" + queueName + ", BKey:" + key);

        }
        System.out.println("CustomerReviver2  Waiting for messages");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("CustomerReviver2 Received '" + message + "'");
            }
        };

        channel.basicConsume(queueName, true, consumer);//队列会自动删除
    }
}
