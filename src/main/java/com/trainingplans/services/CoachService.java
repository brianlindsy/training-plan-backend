package com.trainingplans.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.trainingplans.entities.Coach;
import com.trainingplans.entities.Plan;
import com.trainingplans.repositories.CoachRepository;

@Service
public class CoachService {
	
	@Autowired
	private CoachRepository coachRepository;
	
	@Autowired
	private PlanService planService;
	
	public Coach getCoachById(Long coachId) {
		validateCoach(coachId);
		
		Coach coach = coachRepository.findById(coachId).get();
		
		return coach;
	}
	
	public Coach createNewCoach() {
		Coach coach = new Coach();
		
		Coach saved = coachRepository.save(coach);
		
		return saved;
	}
	
	public Coach addPlanToCoach(Long coachId) {
		
		validateCoach(coachId);
		
		Coach coach = coachRepository.findById(coachId).get();
		
		Plan plan = planService.createNewPlan();
		
		if(coach.getPlans() != null) {
			coach.getPlans().add(plan);
		} else {
			ArrayList<Plan> plans = new ArrayList<Plan>();
			coach.setPlans(plans);
			coach.getPlans().add(plan);
		}
		
		coachRepository.save(coach);
			
		return coach;
	}
	
	public void validateCoach(Long coachId) {
		
		if(!coachRepository.existsById(coachId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Coach with id " + coachId + " not found.");
		}
	}

}
