package com.example.barbershop.service;

import com.example.barbershop.model.Barber;
import com.example.barbershop.repository.BarberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BarberService {

    private final BarberRepository barberRepository;



    public List<Barber> getAllBarbers() {
        return barberRepository.findAll();
    }

    public Optional<Barber> getBarberById(Long id) {
        return barberRepository.findById(id);
    }

    public void addBarber(Barber barber) {
        barberRepository.save(barber);
    }


    // Другие методы, если необходимо
}

