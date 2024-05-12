package com.example.barbershop.controller;

import com.example.barbershop.service.TreatmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class TreatmentController {

    private final TreatmentService treatmentService;



    @GetMapping("/treatments")
    public String showAllTreatments(Model model) {
        model.addAttribute("treatments", treatmentService.getAllTreatments());
        return "treatments-list"; // Имя представления для отображения списка услуг
    }
}
