package com.trainingplans.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.trainingplans.entities.Day;
import com.trainingplans.entities.Plan;
import com.trainingplans.repositories.PlanRepository;

@Service
public class PlanService {
	
	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private DayService dayService;
	
	public Plan createNewPlan() {
		Plan plan = new Plan();
		
		String planId = createNewPlanUniqueId();
		plan.setPlanUniqueId(planId);
		
		plan.setTitle(planId + " training plan.");
		
		Date today = new Date();
		List<Day> week = createNewWeekFromStartDate(today);
		plan.setDays(week);
		
		Plan savedPlan = planRepository.save(plan);
		
		return savedPlan;
	}
	
	public Plan getPlanById(String planId) {
		Plan plan = planRepository.findByPlanUniqueId(planId);
		
		return plan;
	}
	
	public void validatePlan(String planUniqueId) {
		
		if(!planRepository.existsByPlanUniqueId(planUniqueId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plan with id " + planUniqueId + " not found.");
		}
	}
	
	public String createNewPlanUniqueId() {
		UUID uuid = UUID.randomUUID();
		String planId = uuid.toString();
		
		return planId;
	}
	
	public List<Day> createNewWeekFromStartDate(Date start){
		ArrayList<Day> week = new ArrayList<Day>();
		
		for(int i=0;i<7;i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(start);
			calendar.add(Calendar.DATE, i);
			Day day = dayService.createNewDay(calendar.getTime());
			week.add(day);
		}
		
		return week;
	}

}
