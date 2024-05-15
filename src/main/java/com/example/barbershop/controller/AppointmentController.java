package com.example.barbershop.controller;

import com.example.barbershop.model.Appointment;
import com.example.barbershop.model.Barber;
import com.example.barbershop.model.Treatment;
import com.example.barbershop.service.AppointmentService;
import com.example.barbershop.service.BarberService;
import com.example.barbershop.service.EmailService;
import com.example.barbershop.service.TreatmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@AllArgsConstructor
public class AppointmentController {
    //Встреча

    private final AppointmentService appointmentService;
    private final BarberService barberService;
    private final TreatmentService treatmentService;
    private final  EmailService emailService;



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
    public String createAppointment(@RequestParam Long barberId, @RequestParam Long treatmentId,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                                    @RequestParam String clientName, @RequestParam String clientPhoneNumber,
                                    @RequestParam String clientEmail,
                                    Model model) {
        try {

            String confirmationMessage = "Уважаемый " + clientName + ",\n\n" +
                    "Мы рады подтвердить вашу запись на " + dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy 'в' HH:mm")) + ".\n" +
                    "Ожидаем вас и желаем приятного времяпровождения в нашем салоне!\n\n" +
                    "С уважением,\n" +
                    "Команда салона красоты";
            emailService.sendEmail(clientEmail, "Подтверждение записи", confirmationMessage);
            appointmentService.createAppointment(barberId, treatmentId, dateTime, clientName, clientPhoneNumber,clientEmail);

            return "redirect:/barbers";
        } catch (IllegalArgumentException e) {
            // Handle the exception
            model.addAttribute("error", e.getMessage()); // Add error message to the model
            return "error-page"; // Return the error page
        }
    }














    @PostMapping("/appointments/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        // Удаляем запись по ее id
        appointmentService.deleteAppointmentById(id);
        // После удаления перенаправляем на страницу со списком записей
        return "redirect:/appointments";
    }
}

