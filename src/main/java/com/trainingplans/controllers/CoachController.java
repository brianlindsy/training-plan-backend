package com.trainingplans.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainingplans.entities.Coach;
import com.trainingplans.services.CoachService;

@RestController
@CrossOrigin
public class CoachController {
	
	@Autowired
	private CoachService coachService;
	
	@GetMapping(value = "/rest/coach/{coachId}")
	public Coach getCoach(@PathVariable Long coachId) {
		Coach coach = coachService.getCoachById(coachId);
		
		return coach;
	}
	
	@PostMapping(value = "/rest/coach")
	public Coach createNewCoach() {
		Coach coach = coachService.createNewCoach();
		
		return coach;
	}
	
	@PutMapping(value = "/rest/coach/{coachId}/addPlan")
	public Coach caddPlanToCoach(@PathVariable Long coachId) {
		Coach coach = coachService.addPlanToCoach(coachId);
		
		return coach;
	}

}

