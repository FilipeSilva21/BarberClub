package com.BarberClub.DTOs;

import com.BarberClub.models.enums.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) {
}
