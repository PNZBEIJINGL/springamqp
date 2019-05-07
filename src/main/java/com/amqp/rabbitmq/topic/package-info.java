package com.amqp.rabbitmq.topic;
/**
 * 5，TOPIC交换器
 *   P  - >  X( TOPIC)  - *匹配- > Q1 ->C1
 *                      - #匹配- > Q2 ->C2
 * Topic 交换器通过模式匹配分配消息的路由属性， 将路由键和某种模式进行匹配，此时需要绑定一种模式
 * Topic 交换器将路由键和绑定键的字符串切分成单词，单词可以用.隔开。
 * 路由匹配 * ：可以替代一个词  #：可以替代0或者更多的词
 * 1. run recivelogsTopic1
 * 2. run recivelogsTopic2
 * 3. run TopicSender
 *
 */