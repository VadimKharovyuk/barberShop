package com.example.barbershop.service;

import com.example.barbershop.model.Treatment;
import com.example.barbershop.repository.TreatmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;



    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }

    public Optional<Treatment> getTreatmentById(Long id) {
        return treatmentRepository.findById(id);
    }

    // Другие методы, если необходимо
}

