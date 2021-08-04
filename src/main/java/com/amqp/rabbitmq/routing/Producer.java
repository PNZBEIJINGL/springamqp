package com.amqp.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Producer {

    private static final String EXCHANGE_NAME = "exchange_direct_log";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");


        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //exchange-type= direct 直连交换器，routingKeys =bindingkey
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        //routingKeys
        List<String> routingKeys = Arrays.asList("INFO", "WARNING", "ERROR");

        for (String rountingkey : routingKeys) {
            String message =rountingkey+ " Send the message level:" ;
            channel.basicPublish(EXCHANGE_NAME, rountingkey, null, message.getBytes("UTF-8"));
            System.out.println(" Send" + rountingkey + "':'" + message);
        }


        channel.close();
        connection.close();
    }
}
