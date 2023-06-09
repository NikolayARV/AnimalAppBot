package com.example.demoanimalbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class DemoAnimalBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAnimalBotApplication.class, args);
    }

}
