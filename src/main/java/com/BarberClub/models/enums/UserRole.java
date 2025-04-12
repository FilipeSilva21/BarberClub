package com.BarberClub.models.enums;

import lombok.Getter;

@Getter
public enum UserRole {

    Admin("admin"),

    Barber("barber"),

    Client("client");

    private final String role;

    UserRole (String role) {
        this.role = role;
    }

}
