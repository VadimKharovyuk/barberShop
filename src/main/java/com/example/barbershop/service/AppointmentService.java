package com.example.barbershop.service;

import com.example.barbershop.model.Appointment;
import com.example.barbershop.model.Barber;
import com.example.barbershop.model.Treatment;
import com.example.barbershop.repository.AppointmentRepository;
import com.example.barbershop.repository.BarberRepository;
import com.example.barbershop.repository.ClientRepository;
import com.example.barbershop.repository.TreatmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final BarberRepository barberRepository;
    private final ClientRepository clientRepository;
    private final TreatmentRepository treatmentRepository;



    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public void createAppointment(Long barberId, Long treatmentId, LocalDateTime dateTime) {
        Barber barber = barberRepository.findById(barberId).orElseThrow(() -> new IllegalArgumentException("Invalid barber ID"));
        Treatment treatment = treatmentRepository.findById(treatmentId).orElseThrow(() -> new IllegalArgumentException("Invalid treatment ID"));

        Appointment appointment = new Appointment();
        appointment.setBarber(barber);
        appointment.setTreatment(treatment);
        appointment.setDateTime(dateTime); // Устанавливаем переданное время

        appointmentRepository.save(appointment);
    }





    public void deleteAppointmentById(Long id) {
        appointmentRepository.deleteById(id);
    }



    // Другие методы, если необходимо
}

