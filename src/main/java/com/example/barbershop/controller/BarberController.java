package com.example.barbershop.controller;

import com.example.barbershop.model.Barber;
import com.example.barbershop.repository.BarberRepository;
import com.example.barbershop.service.BarberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class BarberController {

    private final BarberService barberService ;

    @GetMapping("/barbers")
    public String showAllBarbers(Model model) {
        model.addAttribute("barbers", barberService.getAllBarbers());
        return "barbers-list"; // Имя представления для отображения списка мастеров
    }


    @GetMapping("/barbers/{id}")
    public String showBarberDetails(@PathVariable Long id, Model model) {
        Barber barber = barberService.getBarberById(id).orElseThrow(() -> new IllegalArgumentException("Invalid barber ID"));
        model.addAttribute("barber", barber);
        return "barber-details"; // Имя представления для отображения информации о мастере
    }
}

