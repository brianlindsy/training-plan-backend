package com.trainingplans.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.trainingplans.entities.Day;
import com.trainingplans.entities.Workout;
import com.trainingplans.entities.WorkoutType;
import com.trainingplans.entities.WorkoutTypeEnum;
import com.trainingplans.repositories.DayRepository;
import com.trainingplans.repositories.WorkoutRepository;

@Service
public class DayService {
	
	@Autowired
	private DayRepository dayRepository;
	
	@Autowired
	private WorkoutRepository workoutRepository;
	
	public Day createNewDay(Date date) {
		Day day = new Day();
		
		day.setDate(date);
		
		Workout workout = new Workout();
		
		WorkoutType type = new WorkoutType();
		type.setWorkoutTypeName(WorkoutTypeEnum.EASY);
		workout.setWorkoutType(type);
		
		workoutRepository.save(workout);
		day.setWorkout(workout);
		
		Day savedDay = dayRepository.save(day);
		
		return savedDay;
	}
	
	public Day updateDay(Long dayId, Day updateTo) {
		
		validateDay(dayId);
		
		Day retrieved = dayRepository.findById(dayId).get();
		
		updateTo.setId(retrieved.getId());
		
		Day saved = dayRepository.save(updateTo);
		
		return saved;
	}
	
	public void validateDay(Long dayId) {
		
		if(!dayRepository.existsById(dayId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Day with id " + dayId + " not found.");
		}
	}

}
