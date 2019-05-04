package com.amqp.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Producer {

    private static final String EXCHANGE_NAME = "server_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");


        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //fanout表示分发，所有的消费者得到同样的队列信息
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        //分发信息
        List<String> keys = Arrays.asList("INFO", "WARNING", "ERROR");
        for (String key : keys) {
            String message = "Send the message level:" + key;
            channel.basicPublish(EXCHANGE_NAME, key, null, message.getBytes());
            System.out.println("SendDirect Send" + key + "':'" + message);
        }


        channel.close();
        connection.close();
    }
}
