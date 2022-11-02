package com.amqp.springsmqp.setp4;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *
 */

@SpringBootApplication(scanBasePackages = {"com.amqp.spring.quicktour.setp4"})
public class BootApplication {

    public static void main(String[] args) {
        //Spring Boot会自动配置基础结构beans
        SpringApplication.run(BootApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(AmqpTemplate template) {
        return args -> template.convertAndSend("myqueue", "This is a springboot demo");
    }

    @Bean
    public Queue myQueue() {
        return new Queue("myqueue");
    }

    @RabbitListener(queues = "myqueue")
    public void listen(String in) {
        System.out.println("#############listen message:"+in);
    }
}
