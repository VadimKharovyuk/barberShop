package com.example.barbershop.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// Ваш сервис электронной почты
@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;


    public void sendEmail(String to, String subject, String emailText) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(emailText); // Установите текст сообщения

        emailSender.send(message);
    }
}


