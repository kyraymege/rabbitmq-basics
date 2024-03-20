package com.kyraymege.rabbitmqbasics.consumer;

import com.kyraymege.rabbitmqbasics.dto.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RMQJsonConsumer {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RMQJsonConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.json.name}")
    public void consume(User user) {
        LOGGER.info("Consuming message: " + user);
    }
}
