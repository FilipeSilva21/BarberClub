package com.BarberClub.DTOs;

import com.BarberClub.models.enums.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ScheduleTimeDTO(Services services, int scheduleTime, LocalDate scheduleDate) {
}
