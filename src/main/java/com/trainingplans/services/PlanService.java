package com.trainingplans.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.trainingplans.entities.Day;
import com.trainingplans.entities.Plan;
import com.trainingplans.entities.Week;
import com.trainingplans.entities.WeeklySummary;
import com.trainingplans.entities.WorkoutTypeEnum;
import com.trainingplans.repositories.PlanRepository;
import com.trainingplans.repositories.WeeklySummaryRepository;
import com.trainingplans.utils.DayComparator;
import com.trainingplans.utils.WeekComparator;

@Service
public class PlanService {
	
	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private WeeklySummaryRepository weeklySummaryRepository;
	
	@Value("${defaultTrainingPlanTitle}")
	private String defaultTrainingPlanTitle;
	
	@Autowired
	private DayService dayService;
	
	public Plan createNewPlan() {
		Plan plan = new Plan();
		
		String planId = createNewPlanUniqueId();
		plan.setPlanUniqueId(planId);
		
		plan.setTitle(defaultTrainingPlanTitle);
		
		Date today = new Date();
		
		ArrayList<Week> weekList = new ArrayList<Week>();
		plan.setWeeks(weekList);
		
		Week week = createNewWeekFromStartDate(today);
		plan.getWeeks().add(week);
		
		Plan savedPlan = planRepository.save(plan);
		
		return savedPlan;
	}
	
	public Plan addNewWeek(String planId) {
		
		validatePlan(planId);
		
		Plan plan = getPlanWithSortedWeeksAndDays(planId);
		
		Date newWeekStartDate = getStartDateOfNewWeek(plan);
		
		Week newWeek = createNewWeekFromStartDate(newWeekStartDate);
		
		plan.getWeeks().add(newWeek);
		
		Plan savedPlan = planRepository.save(plan);
		
		return savedPlan;
	}
	
	public Plan update(Plan updateTo, String planId) {
		
		validatePlan(planId);
		
		Plan plan = getPlanWithSortedWeeksAndDays(planId);
		
		updateTo.setId(plan.getId());
		
		Plan savedPlan = planRepository.save(updateTo);
		
		return savedPlan;
	}
	
	public Plan getPlanById(String planId) {
		
		validatePlan(planId);
		
		Plan plan = getPlanWithSortedWeeksAndDays(planId);
		
		return plan;
	}
	
	public void validatePlan(String planUniqueId) {
		
		if(!planRepository.existsByPlanUniqueId(planUniqueId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plan with id " + planUniqueId + " not found.");
		}
	}
	
	public WeeklySummary createNewWeeklySummary() {
		WeeklySummary weeklySummary = new WeeklySummary();
		
		WeeklySummary weeklySummarySaved = weeklySummaryRepository.save(weeklySummary);
		
		return weeklySummarySaved;
	}
	
	public Date getStartDateOfNewWeek(Plan plan) {
		int numWeeks = plan.getWeeks().size();
		
		Week lastWeekOfPlan = plan.getWeeks().get(numWeeks - 1);
		
		Day lastDayOfPlan = lastWeekOfPlan.getDays().get(6);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(lastDayOfPlan.getDate());
		calendar.add(Calendar.DATE, 1);
		
		return calendar.getTime();
	}
	
	public Plan getPlanWithSortedWeeksAndDays(String planId) {
		Plan plan = planRepository.findByPlanUniqueId(planId);
		
		Collections.sort(plan.getWeeks(), new WeekComparator());
		
		// sort days in a week in ascending order
		for(Week week : plan.getWeeks()) {
			Collections.sort(week.getDays(), new DayComparator());
		}
		
		return plan;
	}
	
	public String createNewPlanUniqueId() {
		UUID uuid = UUID.randomUUID();
		String planId = uuid.toString();
		
		return planId;
	}
	
	public Week createNewWeekFromStartDate(Date start){
		Week week = new Week();
		ArrayList<Day> dayList = new ArrayList<Day>();
		week.setDays(dayList);
		
		for(int i=0;i<7;i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(start);
			calendar.add(Calendar.DATE, i);
			Day day = dayService.createNewDay(calendar.getTime());
			
			week.getDays().add(day);
		}
		
		WeeklySummary weeklySummary = createNewWeeklySummary();
		
		week.setWeeklySummary(weeklySummary);
		
		return week;
	}
	
	public List<String> getWorkoutTypes(){
		
		ArrayList<String> workoutTypes = new ArrayList<String>();
		
		WorkoutTypeEnum[] enums = WorkoutTypeEnum.values();
		
		for(int i=0;i<enums.length;i++) {
			workoutTypes.add(WorkoutTypeEnum.values()[i].name());
		}
		
		return workoutTypes;
	}

}
