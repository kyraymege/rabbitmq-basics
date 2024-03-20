package com.kyraymege.orderservice.publisher;

import com.kyraymege.orderservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    @Value("${rabbitmq.exchange.order.name}")
    private String exchange;

    @Value("${rabbitmq.exchange.order.routing-key}")
    private String routingKey;

    @Value("${rabbitmq.exchange.email.routing-key}")
    private String emailRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToQueue(OrderEvent event) {
        LOGGER.info("Sending message to the queue: " + event.toString());
        rabbitTemplate.convertAndSend(exchange, routingKey, event);
        LOGGER.info("Message sent successfully to the queue");
        rabbitTemplate.convertAndSend(exchange, emailRoutingKey, event);
        LOGGER.info("Message sent successfully to the email queue");
    }

}
