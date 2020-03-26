package com.learn.e05.datetime;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utility {

	final static Logger log = LoggerFactory.getLogger(Utility.class);

	public static LocalTime[] busTiming = { LocalTime.of(8, 15), LocalTime.of(8, 30), LocalTime.of(9, 15),
			LocalTime.of(10, 30), LocalTime.of(14, 15) };

	public static void main(String[] args) {
		log.debug("Bus Schedule from 08:00 {}", getBusSchedule("08:00", Duration.ofHours(3)));
	}

	/**
	 * Method should return a List of String containing the bus timings for a day
	 * given the start time and duration as parameters. - The timing in the list
	 * should be in 24 hour format hh:mm
	 */
	public static List<String> getBusSchedule(String start, Duration frequency) {
		LocalTime startTime = LocalTime.parse(start);
		LocalTime endTime = startTime.plusHours(frequency.toHours());
		List<String> list = new ArrayList<>();
		for (LocalTime time : busTiming) {
			if (time.isBefore(endTime) && time.isAfter(startTime)) {
				list.add(time.format(DateTimeFormatter.ofPattern("hh:mm")));
			}
		}
		return list;

	}

}
