package com.example.barbershop.controller;

import com.example.barbershop.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;



    @GetMapping("/appointments")
    public String showAllAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointments-list"; // Имя представления для отображения списка записей на услуги
    }
}

