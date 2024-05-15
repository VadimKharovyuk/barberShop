package com.example.barbershop.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "appointments")
public class Appointment {
    //Встреча

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "treatment_id", nullable = false)
    private Treatment treatment;

    @ManyToOne
    @JoinColumn(name = "barber_id", nullable = false)
    private Barber barber;

    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private String clientPhoneNumber;

    private String emailClient;

    // Конструкторы, геттеры и сеттеры
}

