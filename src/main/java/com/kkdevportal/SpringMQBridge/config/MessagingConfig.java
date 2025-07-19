package com.kkdevportal.SpringMQBridge.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
* Flow Summary:
* Queue: devportal_queue → Where messages land.
* Exchange: devportal_exchange → Directs messages to queues.
* Binding: Ties the exchange and queue using devportal_key.
* RabbitTemplate: Used in your service to send messages to the exchange.
* JSON Converter: Ensures automatic conversion between Java objects and JSON.
 */


@Configuration // Marks this class as a Spring configuration class
public class MessagingConfig {
    // Constants for queue, exchange, and routing key names
    public static final String QUEUE = "devportal_queue";
    public static final String TOPICEXCHANGE = "devportal_exchange";
    public static final String ROUTINGKEY = "devportal_key";

    /**
     * Creates a RabbitMQ Queue bean.
     * This queue will receive messages routed to it.
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE); // Creates a non-durable queue named 'devportal_queue'
    }

    /**
     * Creates a Topic Exchange bean.
     * Topic exchanges route messages to queues based on a matching routing key pattern.
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPICEXCHANGE); // Creates a topic exchange named 'devportal_exchange'
    }

    /**
     * Binds the queue to the exchange with a routing key.
     * Messages sent to the exchange with this key will be delivered to the queue.
     */
    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queue)                       // Bind the queue
                .to(topicExchange)                 // to the topic exchange
                .with(ROUTINGKEY);                 // using the routing key 'devportal_key'
    }

    /**
     * Defines a message converter to convert Java objects to JSON and vice versa.
     * This allows RabbitTemplate to serialize/deserialize messages as JSON.
     */
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter(); // Converts Java objects to JSON automatically
    }

    /**
     * Configures the RabbitTemplate bean, which is used to send messages.
     * Associates it with the JSON message converter and the RabbitMQ connection.
     */
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory); // Create RabbitTemplate

        rabbitTemplate.setMessageConverter(converter()); // Set JSON message converter
        return rabbitTemplate;
    }
}
