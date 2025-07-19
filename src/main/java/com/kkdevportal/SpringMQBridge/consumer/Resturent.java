package com.kkdevportal.SpringMQBridge.consumer;

import com.kkdevportal.SpringMQBridge.config.MessagingConfig;
import com.kkdevportal.SpringMQBridge.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Resturent {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message received from queue : " + orderStatus.toString());
    }
}
