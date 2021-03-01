package com.trainingplans.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trainingplans.entities.user.trainingplan.Day;
import com.trainingplans.services.DayService;

@RestController
@CrossOrigin
public class DayController {
	
	@Autowired
	private DayService dayService;
	
	@PutMapping(value = "/rest/day/{dayId}")
	public Day updateDay(@RequestBody Day updateTo, @PathVariable Long dayId) {
		
		Day updated = dayService.updateDay(dayId, updateTo);
		
		return updated;
	}

}
