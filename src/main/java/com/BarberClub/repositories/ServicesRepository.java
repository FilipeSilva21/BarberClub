package com.BarberClub.repositories;

import com.BarberClub.models.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services, Long> {

    @Query(value = "SELECT * FROM tb_services WHERE user_id = :userId", nativeQuery = true)
    List<Services> findByUserId(@Param("userId") Long userId);
}
