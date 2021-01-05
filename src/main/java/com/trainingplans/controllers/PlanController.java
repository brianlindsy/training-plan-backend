package com.trainingplans.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trainingplans.entities.Plan;
import com.trainingplans.services.PlanService;

@RestController
@CrossOrigin
public class PlanController {
	
	@Autowired
	private PlanService planService;
	
	@GetMapping(value = "/rest/plan/{planId}")
	public Plan getPlan(@PathVariable String planId) {
		Plan plan = planService.getPlanById(planId);
		
		return plan;
	}
	
	@PostMapping(value = "/rest/plan")
	public Plan createNewPlan() {
		Plan plan = planService.createNewPlan();
		
		return plan;
	}
	
	@PutMapping(value = "/rest/plan/{planId}")
	public Plan updatePlan(@RequestBody Plan updateTo, @PathVariable String planId) {
		Plan plan = planService.update(updateTo, planId);
		
		return plan;
	}
	
	@PutMapping(value = "/rest/plan/{planId}/addWeek")
	public Plan addWeekToPlan(@PathVariable String planId) {
		Plan plan = planService.addNewWeek(planId);
		
		return plan;
	}

}
