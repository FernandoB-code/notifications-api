package com.arabot.notificatiosapi.notificatiosapi.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl {

    @Value("${rabbitmq.queue}")
    String  queueName;

    @Value("${rabbitmq.exchange}")
    String exchange;



    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleMessage(String message) {
        System.out.println("Received message from queue " + queueName + ": " + message);
    }

}
