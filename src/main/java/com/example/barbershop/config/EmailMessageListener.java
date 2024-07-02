package com.example.barbershop.config;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailMessageListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(EmailMessage emailMessage) {

        System.out.println("Received message: " + emailMessage);

    }
}
