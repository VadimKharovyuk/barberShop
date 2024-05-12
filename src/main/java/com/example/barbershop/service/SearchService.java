package com.example.barbershop.service;

import com.example.barbershop.model.Treatment;
import com.example.barbershop.repository.TreatmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchService {
    private final TreatmentRepository treatmentRepository;
    public List<Treatment> searchTreatmentsByName(String name) {
        return treatmentRepository.findByNameContainingIgnoreCase(name);
    }
}
