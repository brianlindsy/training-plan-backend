package com.trainingplans.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.trainingplans.entities.user.User;
import com.trainingplans.entities.user.trainingplan.Plan;
import com.trainingplans.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PlanService planService;
	
	public User getUserById(Long userId) {
		validateUser(userId);
		
		User user = userRepository.findById(userId).get();
		
		return user;
	}
	
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		
		return user;
	}
	
	public User createNewUser(Payload payload) {
		User user = new User();
		
		user.setEmail(payload.getEmail());
		user.setFamilyName((String) payload.get("family_name"));
		user.setGivenName((String) payload.get("given_name"));
		user.setPictureUrl((String) payload.get("picture"));
		
		Plan plan = planService.createNewPlan();
		
		ArrayList<Plan> plans = new ArrayList<Plan>();
		
		plans.add(plan);
		
		user.setPlans(plans);
		
		User saved = userRepository.save(user);
		
		return saved;
	}
	
	public Plan addPlanToUser(Long userId) {
		
		validateUser(userId);
		
		User user = userRepository.findById(userId).get();
		
		Plan plan = planService.createNewPlan();
		
		if(user.getPlans() != null) {
			user.getPlans().add(plan);
		} else {
			ArrayList<Plan> plans = new ArrayList<Plan>();
			user.setPlans(plans);
			user.getPlans().add(plan);
		}
		
		userRepository.save(user);
			
		return plan;
	}
	
	public void validateUser(Long userId) {
		
		if(!userRepository.existsById(userId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " not found.");
		}
	}

}
