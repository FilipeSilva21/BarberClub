package com.BarberClub.controllers;

import com.BarberClub.DTOs.ScheduleTimeDTO;
import com.BarberClub.infra.ExceptionHandler.Exceptions.ServiceNotFoundException;
import com.BarberClub.infra.ExceptionHandler.Exceptions.UserNotFoundException;
import com.BarberClub.models.Schedule;
import com.BarberClub.models.User;
import com.BarberClub.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/services")
public class ScheduleController {

    @Autowired
    private ScheduleSer scheduleService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> scheduleTime(@RequestBody ScheduleTimeDTO dto, @PathVariable User userId, Schedule schedule) {

        try {
            var serviceId = scheduleService.scheduleTime(dto, userId, schedule);

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
            var services = scheduleService.getAllSchedules();

            return ResponseEntity.ok(services);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao listar servicos: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getSchedulesFromUser(@PathVariable("userId") Long userId) {

        try {
            var services = scheduleService.getSchedulesFromClient(userId);

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
            scheduleService.cancelScheduleTime(serviceId);

            return ResponseEntity.ok().build();
        } catch (ServiceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao cancelar servico: " + e.getMessage());
        }
    }
}
