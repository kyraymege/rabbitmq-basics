package com.kyraymege.rabbitmqbasics.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RMQConsumer {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RMQConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consume(String message) {
        LOGGER.info("Consuming message: " + message);
    }
}
