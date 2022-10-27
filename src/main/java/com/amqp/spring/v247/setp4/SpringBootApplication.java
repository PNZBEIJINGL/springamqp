package com.amqp.spring.v247.setp4;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;

/**
 *
 */
public class SpringBootApplication {

    public static void main(String[] args) {
        //Spring Boot会自动配置基础结构beans
        //SpringApplication.run(Application.class, args);
    }

/*    @Bean
    public ApplicationRunner runner(AmqpTemplate template) {
        return args -> template.convertAndSend("myqueue", "foo");
    }*/

    @Bean
    public Queue myQueue() {
        return new Queue("myqueue");
    }

    @RabbitListener(queues = "myqueue")
    public void listen(String in) {
        System.out.println(in);
    }
}
