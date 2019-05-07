package com.amqp.rabbitmq.routing;
/**
 * 4.routing
 *   P  - >  X( DIRECT)  - 完全匹配- > Q1 ->C1
 *                       - 完全匹配- > Q2 ->C2
 * 采用路由的方式对不同的消息进行过滤
 * 使用Direct 交换器,如果消息中的路由routing key和Binding中绑定的binding key 一致，交换器就将信息发送到对应的队列中，
 * 需要注意的是“路由和队列名称需要完全匹配”,Direct 交换器是完全匹配、单播的模式
 * 通过  channel.exchangeDeclare(EXCHANGE_NAME, "direct"); 来设置Direct交换器
 *
 * run CustomerReviver1
 * run CustomerReviver2
 * run Producer
 *
 */