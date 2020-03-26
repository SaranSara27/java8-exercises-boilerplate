package com.learn.e05.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkingDaysExercise {
	
	final static Logger log = LoggerFactory.getLogger(WorkingDaysExercise.class);
	
	public static void main(String[] args) {
		log.debug("NextMonthsWorkingDays {}",getNextMonthsWorkingDays());
	}
	
	
	/**
	 * Method should return a List of String containing the working days for the next month given todays date.
		- Saturday and Sunday should be considered non working days
		- The date returned should be in format dd-mm-yyyy
	 */
	public static List<String> getNextMonthsWorkingDays(){
		
		LocalDate date=LocalDate.now();
		LocalDate oneMonth=date.plusMonths(1);
		List<String> list=new ArrayList<>();
	    while (date.isBefore(oneMonth)) {
	    	date = date.plusDays(1);
	        if (!(date.getDayOfWeek() == DayOfWeek.SATURDAY ||
	        		date.getDayOfWeek() == DayOfWeek.SUNDAY)) {
	        	list.add(date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
	        }
	    }
	    
	    return list;
		
	}

}
