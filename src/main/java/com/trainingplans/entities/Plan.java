package com.trainingplans.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class Plan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(updatable=false)
	private String planUniqueId;
	
	private String title;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="plan_id")
	private List<Day> days;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlanUniqueId() {
		return planUniqueId;
	}

	public void setPlanUniqueId(String planUniqueId) {
		this.planUniqueId = planUniqueId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}

}
