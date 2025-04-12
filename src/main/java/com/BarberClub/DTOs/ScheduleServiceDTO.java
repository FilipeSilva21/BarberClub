package com.BarberClub.DTOs;

import com.BarberClub.models.enums.Services;

import java.time.LocalDateTime;

public record ScheduleServiceDTO(Services services, LocalDateTime horario) {
}
