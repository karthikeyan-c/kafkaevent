package com.dbs.edge.event.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@EnableScheduling
@Slf4j
@Component
public class EventService {
    @Autowired
    private StreamBridge streamBridge;

//    https://refactorfirst.com/spring-cloud-stream-with-kafka-communication

    @Scheduled(fixedDelay = 5)
    public void produceEmployeeDetails() {
        log.info("sending data");
        streamBridge.send("producer1-out-0", "digi kk");
        streamBridge.send("producer2-out-0", "ipe kk");
    }

    @Bean
    public Consumer<String> consumer() {
        return message -> System.out.println("received " + message);
    }

    @Bean
    public Consumer<String> digi() {
        return (message) -> {
            System.out.println("digi received " + message);
        };
    }

    @Bean
    public Consumer<String> ipe() {
        return message -> System.out.println("ipe received " + message);
    }
}
