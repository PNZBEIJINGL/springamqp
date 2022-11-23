package com.amqp.tutorial.step2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Tut2Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Tut2Application.class, args);
    }

}
