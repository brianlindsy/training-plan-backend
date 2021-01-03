package com.trainingplans.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
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
	
	public Coach findCoachByEmail(String email) {
		Coach coach = coachRepository.findByEmail(email);
		
		return coach;
	}
	
	public Coach createNewCoach(Payload payload) {
		Coach coach = new Coach();
		
		coach.setEmail(payload.getEmail());
		coach.setFamilyName((String) payload.get("family_name"));
		coach.setGivenName((String) payload.get("given_name"));
		coach.setPictureUrl((String) payload.get("picture"));
		
		Plan plan = planService.createNewPlan();
		
		ArrayList<Plan> plans = new ArrayList<Plan>();
		
		plans.add(plan);
		
		coach.setPlans(plans);
		
		Coach saved = coachRepository.save(coach);
		
		return saved;
	}
	
	public Plan addPlanToCoach(Long coachId) {
		
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
			
		return plan;
	}
	
	public void validateCoach(Long coachId) {
		
		if(!coachRepository.existsById(coachId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Coach with id " + coachId + " not found.");
		}
	}

}
