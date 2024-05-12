package com.example.barbershop.controller;

import com.example.barbershop.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;



    @GetMapping("/clients")
    public String showAllClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients-list"; // Имя представления для отображения списка клиентов
    }
}

