package com.trainingplans.entities.user.trainingplan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TrainingPhase {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String trainingPhase;
	
	private String trainingPhaseColor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTrainingPhase() {
		return trainingPhase;
	}

	public void setTrainingPhase(String trainingPhase) {
		this.trainingPhase = trainingPhase;
	}

	public String getTrainingPhaseColor() {
		return trainingPhaseColor;
	}

	public void setTrainingPhaseColor(String trainingPhaseColor) {
		this.trainingPhaseColor = trainingPhaseColor;
	}

}
