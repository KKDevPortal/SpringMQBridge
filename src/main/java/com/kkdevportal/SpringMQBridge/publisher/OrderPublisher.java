package com.kkdevportal.SpringMQBridge.publisher;

import com.kkdevportal.SpringMQBridge.config.MessagingConfig;
import com.kkdevportal.SpringMQBridge.dto.Order;
import com.kkdevportal.SpringMQBridge.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/order")
    public String bookOrder(@RequestBody Order order, @RequestParam("resturantName") String resturantName) {
        order.setOrderId(UUID.randomUUID().toString());

        // TODO : Implementation pending
            // restaurant service
            // payment service

        String orderMessage = "[OrderId: " + order.getOrderId() + "] placed successfully in restaurant >> " + resturantName;
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", orderMessage);

        template.convertAndSend(MessagingConfig.TOPICEXCHANGE, MessagingConfig.ROUTINGKEY, orderStatus);

        return orderMessage;

    }
}
