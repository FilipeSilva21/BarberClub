package com.BarberClub.controllers;

import com.BarberClub.DTOs.ScheduleServiceDTO;
import com.BarberClub.infra.ExceptionHandler.Exceptions.ServiceNotFoundException;
import com.BarberClub.infra.ExceptionHandler.Exceptions.UserNotFoundException;
import com.BarberClub.models.Services;
import com.BarberClub.models.User;
import com.BarberClub.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/services")
public class ServicesController {

    @Autowired
    private ServicesService servicesService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> scheduleTime(@RequestBody ScheduleServiceDTO dto, @PathVariable User userId, Services services) {

        try {
            var serviceId = servicesService.scheduleTime(dto, userId, services);

            return ResponseEntity.created(URI.create("v1/services/" + serviceId)).build();
        } catch (UserNotFoundException | ServiceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao agendar serviço: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listSchedules() {

        try {
            var services = servicesService.getAllSchedules();

            return ResponseEntity.ok(services);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao listar servicos: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getSchedulesFromUser(@PathVariable("userId") Long userId) {

        try {
            var services = servicesService.getSchedulesFromClient(userId);

            return ResponseEntity.ok(services);
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar servicos do usuário: " + e.getMessage());
        }
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<?> cancelSchedule(@PathVariable("serviceId") Long serviceId) {

        try {
            servicesService.cancelSchedule(serviceId);

            return ResponseEntity.ok().build();
        } catch (ServiceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao cancelar servico: " + e.getMessage());
        }
    }
}
