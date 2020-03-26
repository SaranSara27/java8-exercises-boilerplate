package com.learn.e05.datetime;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateTimeExercise {

	final static Logger log = LoggerFactory.getLogger(DateTimeExercise.class);

	public static List<Tablet> tabletList = Arrays.asList(new Tablet[] {
			new Tablet("Ciprofloxacin","Apotex Corp",LocalDate.of(2019, 03, 28),LocalDate.of(2022, 03, 02)),
			new Tablet("Codeine","Apotex Corp",LocalDate.of(2020, 01, 01),LocalDate.of(2020, 03, 25)),
			new Tablet("Trazodone-500mg","Apotex Corp",LocalDate.of(2020, 01, 01),LocalDate.of(2020, 03, 25)),
			new Tablet("Ciprofloxacin-500mg","Apotex Corp",LocalDate.of(2020, 03, 28),LocalDate.of(2020, 05, 02)),
			new Tablet("Trazodone","Mylan Pharmaceuticals",LocalDate.of(2020, 01, 01),LocalDate.of(2020, 03, 25)),
			new Tablet("Ciprofloxacin-250mg","Apotex Corp",LocalDate.of(2019, 03, 28),LocalDate.of(2020, 07, 02))
	});
	
	public static void main(String[] args) {
		log.debug("Expiring Tables in 5 months: {}",getExpiringTables(5));
		log.info("List of Tablets, in the ascending order of expiry date:");
		log.debug(getExpiringTabletsSorted().toString());
		log.info("Tablet name with Expiry Period:");
		log.debug(getTabletExpiryPeriod().entrySet().toString());
		log.info("Tablet name with same year expiry:");
		log.debug(getSameYearExpiry().entrySet().toString());
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
	 * return a  List of Tablets, in the ascending order of expire date
	 */
	public static List<String> getExpiringTabletsSorted(){
		return tabletList.stream()
				.sorted(Comparator.comparing(Tablet::getExpiryDate))
		.map(Tablet::getTabletName)
		.collect(Collectors.toList());
		 
	}
	
	/**
	 * return a Map with the tablet name as key and the period between the manufacture date and expire date as value. 
	 * The period should be a string containing years, months and days (ex: 1 year/s 2 month/s 5 day/s , 3 month/s 5 day/s, 3 year/s ..)
	 */
	public static Map<String, String> getTabletExpiryPeriod(){
		return tabletList.stream()
				.collect(Collectors.toMap(Tablet::getTabletName,t->getPeriod(t)));
	}
	
	private static String getPeriod(Tablet t) {
		Period p=Period.between(t.getManufactureDate(), t.getExpiryDate());
		return p.getYears()+" year/s, "+p.getMonths()+" month/s "+p.getDays()+" day/s";
	}
	
	/**
	 * Return a Map with key as manufacturer and value as list of tablet names which are manufactured in the current year and are already expired
	 */
	public static Map<String, List<String>> getSameYearExpiry() {
		return tabletList.stream()
				.filter(t->LocalDate.now().getYear()==t.getManufactureDate().getYear())
				.filter(t->(t.getExpiryDate().isBefore(LocalDate.now())))
				.collect(Collectors.groupingBy(Tablet::getManufacturer,
						Collectors.mapping(Tablet::getTabletName, Collectors.toList())));
		
	}
	
}
