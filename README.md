# SpringMQBridge

A simple Spring Boot project demonstrating RabbitMQ messaging via REST API and MVC web interface.

## 🔧 Features

- Publish messages using REST API
- Listen to a RabbitMQ queue and log received messages
- Built with Spring Boot and RabbitMQ

## 🧰 Technologies

- Java 17+
- Spring Boot 3.x
- RabbitMQ
- Spring AMQP
- Docker (for RabbitMQ setup)
## Curl
```declarative
curl --location 'localhost:9291/api/v1/order?resturantName=Moms%20Magic%20over' \
--header 'Content-Type: application/json' \
--data '{
    "name" : "Butter pancake",
    "qty" : 10,
    "price" : 1800 
}'
```

