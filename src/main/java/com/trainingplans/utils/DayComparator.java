package com.trainingplans.utils;

import java.util.Comparator;

import com.trainingplans.entities.user.trainingplan.Day;

public class DayComparator implements Comparator<Day>{
	
	@Override
	public int compare(Day o1, Day o2) {
		if(o1.getDate().before(o2.getDate())) {
			return -1;
		}
		if(o1.getDate().after(o2.getDate())) {
			return 1;
		}
		return 0;
	}

}
