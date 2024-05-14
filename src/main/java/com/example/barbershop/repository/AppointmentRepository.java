package com.example.barbershop.repository;

import com.example.barbershop.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    int countByBarberIdAndDateTime(Long barberId, LocalDateTime dateTime);
}
