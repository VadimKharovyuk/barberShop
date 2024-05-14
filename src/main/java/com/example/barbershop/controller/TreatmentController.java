package com.example.barbershop.controller;

import com.example.barbershop.model.Treatment;
import com.example.barbershop.service.TreatmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class TreatmentController {

    private final TreatmentService treatmentService;



    @GetMapping("/services")
    public String showServices(Model model) {
        List<Treatment> services = treatmentService.getAllTreatments();
        model.addAttribute("services", services);
        return "services";
    }
}
