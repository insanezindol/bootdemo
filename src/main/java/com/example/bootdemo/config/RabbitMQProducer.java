package com.example.bootdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMQConsumer rabbitMQConsumer;

    public void sendMessage(String message) throws Exception {
        log.info("sendMessage --> " + message);
        rabbitTemplate.convertAndSend("zindol-exchange", "com.zindol.msg", message);
        rabbitMQConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}
