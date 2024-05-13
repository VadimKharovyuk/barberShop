package com.example.barbershop.controller;

import com.example.barbershop.model.Treatment;
import com.example.barbershop.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomePage {
    private final SearchService searchService;

    @GetMapping("/")
    public String homePage(){
        return "home";
    }
    @GetMapping("/search")
    public String searchTreatmentsByName(@RequestParam("serviceName") String serviceName, Model model) {
        // Вызываем метод поиска из сервиса
        List<Treatment> treatments = searchService.searchTreatmentsByName(serviceName);
        // Передаем результаты поиска в модель для отображения на странице
        model.addAttribute("treatments", treatments);
        return "search-results"; // Имя представления для отображения результатов поиска
    }
}
