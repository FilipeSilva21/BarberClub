package com.BarberClub.services;

import com.BarberClub.DTOs.ScheduleServiceDTO;
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

    public Long scheduleTime(ScheduleServiceDTO dto, User user, Services service) {

        if (service.getServices() == com.BarberClub.models.enums.Services.Cabelo){
            service.setPrice(40);
        }
        if (service.getServices() == com.BarberClub.models.enums.Services.Barba){
            service.setPrice(20);
        }
        if (service.getServices() == com.BarberClub.models.enums.Services.CabeloEBarba){
            service.setPrice(55);
        }
        if (service.getServices() == com.BarberClub.models.enums.Services.Sobrancelha){
            service.setPrice(15);
        }

        Services services = new Services(
                null,
                dto.services(),
                dto.schedule(),
                service.getPrice(),
                user
        );

        var scheduledTime = servicesRepository.save(services);

        return scheduledTime.getServiceId();
    }

    public List<Services> getAllSchedules() {
        return servicesRepository.findAll();
    }

    public void cancelSchedule(Long Id) {
        if (!servicesRepository.existsById(Id)) {
            throw new ServiceNotFoundException("Serviço com Id " + Id + " não encontrado para cancelamento.");
        }
        servicesRepository.deleteById(Id);
    }

    public List<Services> getSchedulesFromClient(Long userId) {
        List<Services> userServices = servicesRepository.findByUserId(userId);
        if (userServices.isEmpty()) {
            throw new ServiceNotFoundException("Nenhum serviço encontrado para o usuário com ID " + userId);
        }
        return userServices;
    }
}
