package com.learn.e05.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learn.e02.streams.Player;


public class DateTimeExercise {

	final static Logger log = LoggerFactory.getLogger(DateTimeExercise.class);

	public static List<Tablet> tabletList = Arrays.asList(new Tablet[] {
			new Tablet("Ciprofloxacin","Apotex Corp",LocalDate.of(2020, 03, 28),LocalDate.of(2021, 03, 02)),
			new Tablet("Ciprofloxacin-500mg","Apotex Corp",LocalDate.of(2020, 03, 28),LocalDate.of(2020, 05, 02)),
			new Tablet("Ciprofloxacin-250mg","Apotex Corp",LocalDate.of(2020, 03, 28),LocalDate.of(2020, 07, 02))
	});
	
	public static void main(String[] args) {
		log.debug("Expiring Tables in 5 months: {}",getExpiringTables(5));
		log.debug("List of Tablets, in the ascending order of expiry date:");
		log.debug(getExpiringTabletsSorted().toString());
	}
	
	/**
	 * method should take number of months as parameter and return a List of tablet names expiring within the given months from today
	 */
	public static List<String> getExpiringTables(int months){
		return tabletList.stream()
				.filter(t -> testDate(t.getExpiryDate(),months))
				.map(Tablet::getTabletName)
				.collect(Collectors.toList());
	}
	
	private static boolean testDate(LocalDate input,int months) {
		LocalDate today = LocalDate.now().plusMonths(months);
		boolean output = input.isBefore(today);
		return output;
	}
	
	/**
	 * return a  List of Tablets, in the ascending order of expiry date
	 */
	public static List<String> getExpiringTabletsSorted(){
		return tabletList.stream()
				.sorted(Comparator.comparing(Tablet::getExpiryDate))
		.map(Tablet::getTabletName)
		.collect(Collectors.toList());
		 
	}

}
