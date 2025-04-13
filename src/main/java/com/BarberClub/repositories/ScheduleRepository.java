package com.BarberClub.repositories;

import com.BarberClub.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(value = "SELECT * FROM tb_schedules WHERE user_id = :userId", nativeQuery = true)
    List<Schedule> findByUserId(@Param("userId") Long userId);
}
