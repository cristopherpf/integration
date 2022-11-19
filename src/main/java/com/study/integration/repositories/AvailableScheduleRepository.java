package com.study.integration.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.study.integration.entities.AvailableSchedule;

@NamedQueries({
	@NamedQuery(name = "AvailableScheduleRepository.findByPersonId",
			    query = "select as from AvailableSchedule as where as.person.id = :personId")
})
public interface AvailableScheduleRepository extends JpaRepository<AvailableSchedule, Long> {

	List<AvailableSchedule> findByPersonId(@Param("personId") Long personId);
	
}
