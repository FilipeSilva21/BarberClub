package com.BarberClub.services;

import com.BarberClub.DTOs.CreateServiceDTO;
import com.BarberClub.infra.ExceptionHandler.Exceptions.ServiceNotFoundException;
import com.BarberClub.models.User;
import com.BarberClub.models.Services;
import com.BarberClub.repositories.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;

    public Long scheduleService(CreateServiceDTO dto, User user) {

        Services services = new Services(
                null,
                dto.services(),
                dto.description(),
                user
        );

        var scheduledService = servicesRepository.save(services);

        return scheduledService.getServiceId();
    }

    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    public void cancelService(Long Id) {
        if (!servicesRepository.existsById(Id)) {
            throw new ServiceNotFoundException("Serviço com Id " + Id + " não encontrado para cancelamento.");
        }
        servicesRepository.deleteById(Id);
    }

    public List<Services> getServicesFromClient(Long userId) {
        List<Services> userServices = servicesRepository.findByUserId(userId);
        if (userServices.isEmpty()) {
            throw new ServiceNotFoundException("Nenhum serviço encontrado para o usuário com ID " + userId);
        }
        return userServices;
    }
}
