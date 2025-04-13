package com.BarberClub.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long serviceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "services")
    private com.BarberClub.models.enums.Services services;

    @Column(name = "schedule")
    private LocalDateTime schedule;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("user")
    public User client;
}
