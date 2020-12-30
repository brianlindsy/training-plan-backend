package com.trainingplans.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.trainingplans.entities.Plan;

@Component
public interface PlanRepository extends CrudRepository<Plan, Long> {

	Plan findById(long id);
	
	Plan findByPlanUniqueId(String planId);
	
	boolean existsByPlanUniqueId(String planId);
}
