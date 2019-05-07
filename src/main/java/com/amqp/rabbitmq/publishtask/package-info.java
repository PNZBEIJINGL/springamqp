package com.amqp.rabbitmq.publishtask;
/**
 * 2，实现任务分发
 * 积累了多个任务，多个消费者来处理
 *  P  -> Q  ->C1,C2
 *
 * queueDeclare(String queue,boolean durable,boolean exlusive,boolean autoDelete,Map<String, Object> arguments)
 * durable ：为true来实现Queue 持久化，服务器重启后Queue会继续存在,但是不保证消息存在，
 * exclusive :表示是否是排他队列，
 * authoDelete表示自动删除，true表示队列会再没有任何订阅的消费者时被自动删除，常见于需要临时队列的场景
 *
 * run Work1
 * run Work2
 * run Producer
 */