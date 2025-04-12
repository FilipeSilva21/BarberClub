package com.BarberClub.controllers;

import com.BarberClub.DTOs.CreateServiceDTO;
import com.BarberClub.infra.ExceptionHandler.Exceptions.ServiceNotFoundException;
import com.BarberClub.infra.ExceptionHandler.Exceptions.UserNotFoundException;
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
    public ResponseEntity<?> scheduleService(@RequestBody CreateServiceDTO dto, @PathVariable User userId) {

        try {
            var serviceId = servicesService.scheduleService(dto, userId);

            return ResponseEntity.created(URI.create("v1/services/" + serviceId)).build();
        } catch (UserNotFoundException | ServiceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao agendar serviço: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listServices() {

        try {
            var services = servicesService.getAllServices();

            return ResponseEntity.ok(services);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao listar servicos: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getServicesFromUser(@PathVariable("userId") Long userId) {

        try {
            var services = servicesService.getServicesFromClient(userId);

            return ResponseEntity.ok(services);
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar servicos do usuário: " + e.getMessage());
        }
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<?> cancelService(@PathVariable("serviceId") Long serviceId) {

        try {
            servicesService.cancelService(serviceId);

            return ResponseEntity.ok().build();
        } catch (ServiceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao cancelar servico: " + e.getMessage());
        }
    }
}
