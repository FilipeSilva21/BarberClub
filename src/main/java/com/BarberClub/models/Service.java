package com.BarberClub.models;

import com.BarberClub.models.enums.Services;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "services")
    private Services services;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "client_id")
    public User client;
}
