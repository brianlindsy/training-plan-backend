package com.trainingplans.controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.trainingplans.entities.Coach;
import com.trainingplans.entities.Plan;
import com.trainingplans.services.CoachService;
import com.trainingplans.utils.GoogleUtils;

@RestController
@CrossOrigin
public class CoachController {
	
	@Autowired
	private CoachService coachService;
	
	@Autowired
	private GoogleUtils googleUtils;
	
	@GetMapping(value = "/rest/coach/{coachId}")
	public Coach getCoach(@PathVariable Long coachId) {
		Coach coach = coachService.getCoachById(coachId);
		
		return coach;
	}
	
	@PostMapping(value = "/rest/coach/validate")
	public Coach getCoachByUserId(@RequestBody String tokenId) {
		Payload payload;
		try {
			payload = googleUtils.validateAndReturnIdTokenPayload(tokenId);
		} catch (GeneralSecurityException | IOException e) {
			throw new ResponseStatusException(
			           HttpStatus.SC_INTERNAL_SERVER_ERROR, "Could not validate user.", e);
		}
		
		Coach coachToReturn = null;
		
		if(payload != null) {
			String email = payload.getEmail();
			if(coachService.findCoachByEmail(email) != null){
				coachToReturn = coachService.findCoachByEmail(email);
			} else {
				coachToReturn = coachService.createNewCoach(payload);
			}
		}
		return coachToReturn;
	}
	
	@PutMapping(value = "/rest/coach/{coachId}/addPlan")
	public Plan addPlanToCoach(@PathVariable Long coachId) {
		Plan addedPlan = coachService.addPlanToCoach(coachId);
		
		return addedPlan;
	}

}

