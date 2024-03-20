package com.kyraymege.orderservice.controller;

import com.kyraymege.orderservice.dto.Order;
import com.kyraymege.orderservice.dto.OrderEvent;
import com.kyraymege.orderservice.publisher.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public String publishOrder(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent("PENDING", "Order is pending!", order);
        orderProducer.sendToQueue(orderEvent);
        return "Order published successfully";
    }
}
