package com.learn.e01.lamdas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LambdaExercise {
	
	final static Logger log=LoggerFactory.getLogger(LambdaExercise.class);
	
	private static List<String> countries;
	
	private static Map<String, String> countryCapitals;
	
	public static void main(String[] args) {
		countries=new ArrayList<>();
		countries.add("INDIA");
		countries.add("CHINA");
		countries.add("TURKEY");
		countries.add("AUSTRALIA");
		countries.add("GERMANY");
		
		countryCapitals=new HashMap<>();
		countryCapitals.put("INDIA", "NEW DELHI");
		countryCapitals.put("CHINA", "BEIJING");
		countryCapitals.put("AUSTRALIA", "CANBERRA");
		countryCapitals.put("GERMANY", "BERLIN");
		
		log.info("List of counties are as follows :");
		displayCountries();
		
		log.info("country and its captial are as follows :");
		displayCountryCapitals();
		
		log.info("Sorted country list in reverse order as follows::");
		sortCountriesByName();
		
		log.info("Sorted countries based on length:");
		sortCountriesBylength();
		
		log.info("Removed country whose length is greater than 6. Final list:");
		countries.removeIf(x->removeCountry(x));
		countries.forEach(country->log.debug(country));
	}
	
	/**
	 * Iterate through the List and display the list using forEach(Consumer) method
	 */
	private static void displayCountries() {
		Consumer<String> country = new Consumer<String>() {
			@Override
			public void accept(String arg0) {
				log.debug(arg0);
			}
		};
		
		countries.forEach(country);
	}
	
	/**
	 * Iterate through the Map and display the map using forEach(BiConsumer) method
	 */
	private static void displayCountryCapitals() {
		BiConsumer<String,String> bipolar = new BiConsumer<String,String>() {
			@Override
			public void accept(String country,String capital) {
				log.debug("{} : {}",country,capital);
			}
		};
		
		countryCapitals.forEach(bipolar);
	}
	
	/**
	 * Sort the List using Comparator(Lambda Exp). Sort in the reverse alphabetical order
	 */
	private static void sortCountriesByName() {
		Comparator<String> comparator = (c1,c2)->c1.compareTo(c2);
		countries.sort(comparator.reversed());
		countries.forEach(country->log.debug(country));

	}
	
	/**
	 * Sort the List using Comparator, in descending order of number of characters in the country name.
	 * If the number of characters are same for a country, it should be sorted alphabetically
	 * (Use Comparator's static/default methods)
	 */
	private static void sortCountriesBylength() {
		countries.sort(Comparator.comparing(String::length).reversed().thenComparing(String::toString));
		countries.forEach(country->log.debug(country));
		
	}
	
	/**
	 * remove the countries whose name is greater than 6 characters
	 */
	private static boolean removeCountry(String name) {
		return name.length()>6?true:false;
	}
}
