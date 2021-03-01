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
import com.trainingplans.entities.user.User;
import com.trainingplans.entities.user.trainingplan.Plan;
import com.trainingplans.services.UserService;
import com.trainingplans.utils.GoogleUtils;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GoogleUtils googleUtils;
	
	@GetMapping(value = "/rest/user/{userId}")
	public User getUser(@PathVariable Long userId) {
		User user = userService.getUserById(userId);
		
		return user;
	}
	
	@PostMapping(value = "/rest/user/validate")
	public User createUserUserByGoogleToken(@RequestBody String tokenId) {
		
		Payload payload;
		try {
			payload = googleUtils.validateAndReturnIdTokenPayload(tokenId);
		} catch (GeneralSecurityException | IOException e) {
			throw new ResponseStatusException(
			           HttpStatus.SC_INTERNAL_SERVER_ERROR, "Could not validate user.", e);
		}
		
		User userToReturn = null;
		
		if(payload != null) {
			String email = payload.getEmail();
			if(userService.findUserByEmail(email) != null){
				userToReturn = userService.findUserByEmail(email);
			} else {
				userToReturn = userService.createNewUser(payload);
			}
		}
		return userToReturn;
	}
	
	@PutMapping(value = "/rest/user/{userId}/addPlan")
	public Plan addPlanToUser(@PathVariable Long userId) {
		Plan addedPlan = userService.addPlanToUser(userId);
		
		return addedPlan;
	}

}

