package com.learn.e05.datetime;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankDepositExercise {
	
	final static Logger log = LoggerFactory.getLogger(BankDepositExercise.class);
	
	public static void main(String[] args) {
		log.debug("MaturityDate : {}",getMaturityDate("12/03/2010",Period.of(2, 2, 2)));
		log.debug("InvestmentPeriod : {}",getInvestmentPeriod("12/03/2010","12/08/2012"));
	}
	
	/**
	 * Method should take investment date and duration as input and return the maturity date as a string in the format <dd-mmm-yyyy>
	 * 
	 */
	public static String getMaturityDate(String investmentDate, Period duration) {
		LocalDate invDate=LocalDate.parse(investmentDate,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate matDate=invDate.plusYears(duration.getYears()).plusMonths(duration.getMonths()).plusDays(duration.getDays());
		return matDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
	}
	
	/**
	 * Method should take investment date and maturity dates as input and return the duration as a string in the format <x years, y months>
	 * 
	 */
	public static String getInvestmentPeriod(String investmentDate, String maturityDate) {
		DateTimeFormatter format=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate invDate=LocalDate.parse(investmentDate,format);
		LocalDate matDate=LocalDate.parse(maturityDate,format);
		Period p=Period.between(invDate, matDate);
		return p.getYears()+" years, "+p.getMonths()+" months";
	}

}
