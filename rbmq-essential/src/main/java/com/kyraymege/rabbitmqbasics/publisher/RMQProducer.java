package com.kyraymege.rabbitmqbasics.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routing;

    private static final Logger LOGGER = LoggerFactory.getLogger(RMQProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public RMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String message) {
        LOGGER.info("Sending message: " + message);
        rabbitTemplate.convertAndSend(exchange, routing, message);
    }
}
