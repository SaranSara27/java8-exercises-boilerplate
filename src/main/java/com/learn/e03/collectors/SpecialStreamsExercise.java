package com.learn.e03.collectors;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SpecialStreamsExercise {
	
	final static Logger log=LoggerFactory.getLogger(SpecialStreamsExercise.class);
	
	public static void main(String[] args) {
		
		log.info("squares of odd multiples of 3 from 20 to 50 : ");
		log.debug(getSquaresOfThree().toString());
		log.info("First 20 multiples Of Five");
		log.debug(Arrays.toString(getMultiplesOfFive()));
	}
	
	/**
	 * Use IntStream to generate a range of numbers from 20 to 50 inclusive. 
	 * From the stream, store the square of odd multiples of 3 in an LinkedList and return it
	 * @return 
	 */
	public static LinkedList<Integer> getSquaresOfThree() {
		return IntStream.range(20, 51).filter(n->validate(n)).map(num->num*num).boxed().collect(Collectors.toCollection(()->new LinkedList<Integer>()));
	}
	
	private static boolean validate(int number) {
		if(number % 3==0 && number % 2==1) {
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Generate a infinite stream with multiples of 5 and collect the first 20 multiples in an array and return the array
	 */
	public static Integer[] getMultiplesOfFive() {
		
		List<Integer> list=Stream.iterate(5, n->n+5).limit(20).collect(Collectors.toList());
		Integer[] array=new Integer[list.size()];
		return list.toArray(array);
	}
}
