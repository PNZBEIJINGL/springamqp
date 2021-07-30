package com.amqp.rabbitmq.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 创建10个工作
 */
public class NewTask {

    public final static String QUEUE_NAME = "tutorial.queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //使用工厂创建1个连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        try {
            //创建1个通道，声明要关注的队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            //发送10个任务
            for (int i = 0; i < 10; i++) {
                String message = "task message.NO." + i;
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
            }
        } finally {
            //关闭通道和连接
            //channel.close();
            //connection.close();
        }
    }
}
