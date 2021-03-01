package com.trainingplans.entities.user.trainingplan;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class WeeklySummary {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	private TrainingPhase trainingPhase;
	
	private String coachingNotes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TrainingPhase getTrainingPhase() {
		return trainingPhase;
	}

	public void setTrainingPhase(TrainingPhase trainingPhase) {
		this.trainingPhase = trainingPhase;
	}

	public String getCoachingNotes() {
		return coachingNotes;
	}

	public void setCoachingNotes(String coachingNotes) {
		this.coachingNotes = coachingNotes;
	}

}
