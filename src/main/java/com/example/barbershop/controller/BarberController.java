package com.example.barbershop.controller;

import com.example.barbershop.model.Barber;
import com.example.barbershop.repository.BarberRepository;
import com.example.barbershop.service.BarberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/barbers/new")
    public String showBarberForm(Model model) {
        model.addAttribute("barber", new Barber());
        return "add-barber-form"; // Предполагается, что у вас есть HTML-шаблон для формы добавления барбера с именем "add-barber-form"
    }

    @PostMapping("/barbers-add")
    public String addBarber(@ModelAttribute Barber barber) {
        barberService.addBarber(barber);
        return "redirect:/barbers"; // Предполагается, что у вас есть страница со списком барберов с адресом "/barbers"
    }
}

