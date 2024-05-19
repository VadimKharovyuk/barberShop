package com.example.barbershop.config;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
public class EmailMessage implements Serializable {
    private Long barberId;
    private Long treatmentId;
    private LocalDateTime dateTime;
    private String clientName;
    private String clientPhoneNumber;
    private String clientEmail;


}

