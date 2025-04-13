package com.BarberClub.models;

import com.BarberClub.models.enums.Services;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "services")
    private Services services;

    @Column(name = "scheduleTime")
    private LocalDateTime scheduleTime;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("user")
    public User client;
}
