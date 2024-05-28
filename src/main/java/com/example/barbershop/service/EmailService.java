package com.example.barbershop.service;

import com.example.barbershop.config.EmailMessage;
import com.example.barbershop.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void sendEmail(EmailMessage emailMessage) {
        String confirmationMessage = "Уважаемый " + emailMessage.getClientName() + ",\n\n" +
                "Мы рады подтвердить вашу запись на " + emailMessage.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy 'в' HH:mm")) + ".\n" +
                "Ожидаем вас и желаем приятного времяпровождения в нашем салоне!\n\n" +
                "С уважением,\n" +
                "Команда салона красоты";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailMessage.getClientEmail());
        message.setSubject("Подтверждение записи");
        message.setText(confirmationMessage);

        emailSender.send(message);
    }
}