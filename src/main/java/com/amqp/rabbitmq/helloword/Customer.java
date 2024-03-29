package com.amqp.rabbitmq.helloword;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 */
public class Customer {


    private final static String QUEUE_NAME = "rabbitMQ.test";

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Customer PROCESS");
        // 创建连接工厂,设置RabbitMQ地址
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        System.out.println("connection...");
        //创建一个新的连接
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        try {
            //声明要关注的队列
            //channel.queueBind(QUEUE_NAME, null, null);
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("Customer Waiting Received messages");


            //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
            // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDeliveryå
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body)
                        throws IOException {

                    //envelope主要存放生产者相关信息（比如交换机、路由key等）body是消息实体
                    String message = new String(body, "UTF-8");
                    System.out.println("Customer Received '" + message + "'");
                }
            };

            //自动回复队列应答 -- RabbitMQ中的消息确认机制
            channel.basicConsume(QUEUE_NAME, true, consumer);

        } finally {
            //关闭通道和连接
            //channel.close();
            //connection.close();
        }
    }
}
