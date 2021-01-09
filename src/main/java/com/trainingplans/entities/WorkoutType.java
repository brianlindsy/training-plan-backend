package com.trainingplans.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WorkoutType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private WorkoutTypeEnum workoutTypeName;
	
	private String workoutTypeColor;
	
	private String workoutTypeDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WorkoutTypeEnum getWorkoutTypeName() {
		return workoutTypeName;
	}

	public void setWorkoutTypeName(WorkoutTypeEnum workoutTypeName) {
		this.workoutTypeName = workoutTypeName;
	}

	public String getWorkoutTypeColor() {
		return workoutTypeColor;
	}

	public void setWorkoutTypeColor(String workoutTypeColor) {
		this.workoutTypeColor = workoutTypeColor;
	}

	public String getWorkoutTypeDescription() {
		return workoutTypeDescription;
	}

	public void setWorkoutTypeDescription(String workoutTypeDescription) {
		this.workoutTypeDescription = workoutTypeDescription;
	}

}
