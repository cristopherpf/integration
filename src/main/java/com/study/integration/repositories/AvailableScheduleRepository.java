package com.study.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.integration.entities.AvailableSchedule;

public interface AvailableScheduleRepository extends JpaRepository<AvailableSchedule, Long> {

	
}
