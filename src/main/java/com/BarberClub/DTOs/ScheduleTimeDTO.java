package com.BarberClub.DTOs;

import com.BarberClub.models.enums.Services;

import java.time.LocalDateTime;

public record ScheduleTimeDTO(Services services, LocalDateTime scheduleTime) {
}
