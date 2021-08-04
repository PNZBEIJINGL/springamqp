package com.amqp.rabbitmq.routing;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class CustomerReviver1 {
    private static final String EXCHANGE_NAME = "exchange_direct_log";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");


        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明交换机并获取匿名队列
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");


        //设置bindingkey
        List<String> bindingkeys = Arrays.asList("INFO", "WARNING","ERROR");
        for (String bindingkey : bindingkeys) {
            channel.queueBind(queueName,EXCHANGE_NAME,bindingkey);
            System.out.println("CustomerReviver1 exchange:"+EXCHANGE_NAME+"," +
                    " queue:"+queueName+", BKey:" + bindingkey);

        }

        System.out.println("CustomerReviver1  Waiting for messages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
            System.out.println(" [x] do print");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

    }
}
