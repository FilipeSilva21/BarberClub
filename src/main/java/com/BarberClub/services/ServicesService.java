package com.BarberClub.services;

import com.BarberClub.DTOs.CreateServiceDTO;
import com.BarberClub.models.User;
import com.BarberClub.repositories.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;

    public Long CreateService(CreateServiceDTO createServiceDTO, User userId){

        var service = new com.BarberClub.models.Service(
                null,
                createServiceDTO.services(),
                createServiceDTO.description(),
                userId
        );

        var serviceSaved = servicesRepository.save(service);

        return serviceSaved.getId();
    }

    public Optional<com.BarberClub.models.Service> getUserById(Long id) {
        return servicesRepository.findById(id);
    }

    public List<com.BarberClub.models.Service> getAllUsers() {
        return servicesRepository.findAll();
    }

    public void cancelServiceById(Long id) {

        if (!servicesRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario com Id " + id + " nao foi encontrado");
        }

        servicesRepository.deleteById(id);
    }
}
