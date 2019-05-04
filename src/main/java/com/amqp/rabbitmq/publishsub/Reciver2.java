package com.amqp.rabbitmq.publishsub;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Reciver2 {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, null);

        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, null);

        System.out.println("Reciver2 waiting! queueName=" + queueName);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {

                //envelope主要存放生产者相关信息（比如交换机、路由key等）body是消息实体
                String message = new String(body, "UTF-8");
                System.out.println("Reciver2 : '" + message + "'");

            }
        };
        
        //队列会自动删除
        channel.basicConsume(queueName, true, consumer);
    }
}
