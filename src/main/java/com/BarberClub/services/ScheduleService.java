package com.BarberClub.services;


import com.BarberClub.DTOs.ScheduleTimeDTO;
import com.BarberClub.infra.ExceptionHandler.Exceptions.ServiceNotFoundException;
import com.BarberClub.models.User;
import com.BarberClub.models.Schedule;
import com.BarberClub.models.enums.Services;
import com.BarberClub.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Long scheduleTime(ScheduleTimeDTO dto, User user, Schedule schedule) {

        if (schedule.getServices() == Services.Cabelo){
            schedule.setPrice(40);
        }
        if (schedule.getServices() == Services.Barba){
            schedule.setPrice(20);
        }
        if (schedule.getServices() == Services.CabeloEBarba){
            schedule.setPrice(55);
        }
        if (schedule.getServices() == Services.Sobrancelha){
            schedule.setPrice(15);
        }

        Schedule schedules = new Schedule(
                null,
                dto.services(),
                dto.scheduleTime(),
                schedule.getPrice(),
                user
        );

        var scheduledTime = scheduleRepository.save(schedules);

        return scheduledTime.getScheduleId();
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public void cancelScheduleTime(Long Id) {

        if (!scheduleRepository.existsById(Id))
            throw new ServiceNotFoundException("Serviço com Id " + Id + " não encontrado para cancelamento.");

        scheduleRepository.deleteById(Id);
    }

    public List<Schedule> getSchedulesFromClient(Long userId) {

        List<Schedule> userSchedulesTimes = scheduleRepository.findByUserId(userId);

        if (userSchedulesTimes.isEmpty())
            throw new ServiceNotFoundException("Nenhum serviço encontrado para o usuário com ID " + userId);

        return userSchedulesTimes;
    }
}
