package com.trainingplans.entities.user.trainingplan;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Week {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="week_id")
	private List<Day> days;
	
	private int weekNumber;
	
	@OneToOne(cascade=CascadeType.ALL)
	private WeeklySummary weeklySummary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}

	public int getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}

	public WeeklySummary getWeeklySummary() {
		return weeklySummary;
	}

	public void setWeeklySummary(WeeklySummary weeklySummary) {
		this.weeklySummary = weeklySummary;
	}

}
