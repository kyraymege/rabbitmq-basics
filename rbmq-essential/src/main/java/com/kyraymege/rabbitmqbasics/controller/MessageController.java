package com.kyraymege.rabbitmqbasics.controller;

import com.kyraymege.rabbitmqbasics.publisher.RMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final RMQProducer rmqProducer;

    public MessageController(RMQProducer rmqProducer) {
        this.rmqProducer = rmqProducer;
    }

    // http://localhost:8080/api/publish?message=Hello
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        rmqProducer.send(message);
        return ResponseEntity.ok("Message sent to the RabbitMQ");
    }


}
