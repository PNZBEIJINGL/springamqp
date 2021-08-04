package com.amqp.rabbitmq.routing;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class CustomerReviver2 {

    private static final String EXCHANGE_NAME = "exchange_direct_log";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");


        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");


        //channel 绑定bindingkeys
        List<String> bindingkeys = Arrays.asList("ERROR");
        for (String bindingkey : bindingkeys) {
            channel.queueBind(queueName, EXCHANGE_NAME, bindingkey);
            System.out.println("CustomerReviver2 exchange:" + EXCHANGE_NAME + "," +
                    " queue:" + queueName + ", BKey:" + bindingkey);

        }
        System.out.println("CustomerReviver2  Waiting for messages");


        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            //routingKey=bindingKey时才起作用
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
            System.out.println(" [x] do saving");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });



    }
}
