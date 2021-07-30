package com.amqp.rabbitmq.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 */
public class Recv3 {


    public final static String QUEUE_NAME = "tutorial.queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Customer PROCESS");
        // 创建连接工厂,设置RabbitMQ地址
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        System.out.println("connection...");
        //创建一个新的连接
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            //声明要关注的队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("Customer Waiting Received messages");

            channel.basicQos(1);
            // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDeliveryå
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");

                try {
                    doWork(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(" [x] Done");
                }
            };
            boolean autoAck = false; // acknowledgment is covered below
            channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
            });

        } finally {
            //关闭通道和连接
            //channel.close();
            //connection.close();
        }
    }

    //模拟执行任务
    private static void doWork(String task) throws InterruptedException {
        Random random1 = new Random();
        Integer time = random1.nextInt(5000);
        System.out.println(" [x] deal time" + time);
        Thread.sleep(time);
    }
}
