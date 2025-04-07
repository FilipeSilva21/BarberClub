package com.BarberClub.repositories;

import com.BarberClub.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<Service, Long> {
}
