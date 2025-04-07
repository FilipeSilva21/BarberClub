package com.BarberClub.DTOs;

import com.BarberClub.models.User;
import com.BarberClub.models.enums.Services;

public record CreateServiceDTO(Services services, String description, User userId) {}
