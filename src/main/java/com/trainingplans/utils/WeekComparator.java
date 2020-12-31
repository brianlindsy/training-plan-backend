package com.trainingplans.utils;

import java.util.Comparator;

import com.trainingplans.entities.Week;

public class WeekComparator implements Comparator<Week>{
	
	@Override
	public int compare(Week o1, Week o2) {
		if(o1.getWeekNumber() < o2.getWeekNumber()) {
			return -1;
		}
		if(o1.getWeekNumber() > o2.getWeekNumber()) {
			return 1;
		}
		return 0;
	}

}
