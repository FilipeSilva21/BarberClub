package com.BarberClub.controllers;

import com.BarberClub.DTOs.CreateServiceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class ServicesController {

    public ResponseEntity<?> scheduledServices(CreateServiceDTO createServiceDTO){

        return ResponseEntity.ok().build();
    }
}
