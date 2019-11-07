package com.example.bootdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
public class RabbitMQConsumer {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        log.info("receiveMessage --> " + message);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
