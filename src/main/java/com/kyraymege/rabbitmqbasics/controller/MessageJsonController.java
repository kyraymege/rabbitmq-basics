package com.kyraymege.rabbitmqbasics.controller;

import com.kyraymege.rabbitmqbasics.dto.User;
import com.kyraymege.rabbitmqbasics.publisher.RMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageJsonController {

    private final RMQJsonProducer rmqProducer;

    public MessageJsonController(RMQJsonProducer rmqProducer) {
        this.rmqProducer = rmqProducer;
    }

    // http://localhost:8080/api/publish?message=Hello
    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody User user) {
        rmqProducer.send(user);
        return ResponseEntity.ok("Message sent to the RabbitMQ");
    }

}
