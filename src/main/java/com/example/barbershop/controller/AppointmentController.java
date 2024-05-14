package com.example.barbershop.controller;

import com.example.barbershop.model.Barber;
import com.example.barbershop.model.Treatment;
import com.example.barbershop.service.AppointmentService;
import com.example.barbershop.service.BarberService;
import com.example.barbershop.service.TreatmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class AppointmentController {
    //Встреча

    private final AppointmentService appointmentService;
    private final BarberService barberService;
    private final TreatmentService treatmentService;



    @GetMapping("/appointments")
    public String showAllAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointments-list"; // Имя представления для отображения списка записей на услуги
    }
    @GetMapping("/appointments/create")
    public String showAppointmentForm(Model model) {
        // Получаем список всех барберов
        List<Barber> barbers = barberService.getAllBarbers();
        List<Treatment> treatments =treatmentService.getAllTreatments();
        model.addAttribute("barbers", barbers);
        model.addAttribute("treatments",treatments);
        return "appointment-form";
    }

    @PostMapping("/appointments")
    public String createAppointment(@RequestParam Long barberId, Model model) {
        // Создаем запись клиента
        appointmentService.createAppointment(barberId);
        // Здесь можно добавить логику для подтверждения записи
        return "redirect:/"; // Перенаправляем на главную страницу
    }

    @PostMapping("/appointments/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        // Удаляем запись по ее id
        appointmentService.deleteAppointmentById(id);
        // После удаления перенаправляем на страницу со списком записей
        return "redirect:/appointments";
    }
}

