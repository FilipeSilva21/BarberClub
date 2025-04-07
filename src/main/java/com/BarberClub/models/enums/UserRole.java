package com.BarberClub.models.enums;

public enum UserRole {
    Barber("barber"),

    Client("client");

    private String role;

    UserRole (String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
