package com.example.bootdemo.controller;

import com.example.bootdemo.config.RabbitMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbit")
@Slf4j
public class RabbitController {

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @GetMapping(value = "/send/{message}")
    public JSONObject get(@PathVariable String message) {
        JSONObject obj = new JSONObject();
        try {
            rabbitMQProducer.sendMessage(message);
            obj.put("code", "100200");
            obj.put("msg", "success");
            obj.put("data", null);
        } catch (Exception e) {
            log.error(e.getMessage());
            obj.put("code", "100103");
            obj.put("msg", "fail send rabbitmq");
            obj.put("data", null);
        }
        return obj;
    }

}
