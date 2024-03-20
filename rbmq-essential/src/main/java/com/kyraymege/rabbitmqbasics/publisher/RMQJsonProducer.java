package com.kyraymege.rabbitmqbasics.publisher;

import com.kyraymege.rabbitmqbasics.dto.User;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routing;

    private final RabbitTemplate rabbitTemplate;
    private static final Logger Logger = org.slf4j.LoggerFactory.getLogger(RMQJsonProducer.class);

    public RMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(User user) {
        Logger.info("JSON Sending message: " + user);
        rabbitTemplate.convertAndSend(exchange, routing, user);
    }
}
