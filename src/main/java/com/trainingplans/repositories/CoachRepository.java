package com.trainingplans.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.trainingplans.entities.Coach;

@Component
public interface CoachRepository extends CrudRepository<Coach, Long> {
	Coach findByEmail(String email);
}
