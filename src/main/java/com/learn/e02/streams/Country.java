package com.learn.e02.streams;

public class Country {
	private int countryId;
	private String CountryName;
	
	public Country(int countryId, String countryName) {
		super();
		this.countryId = countryId;
		CountryName = countryName;
	}
	
	public int getCountryId() {
		return countryId;
	}
	
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	
	public String getCountryName() {
		return CountryName;
	}
	
	public void setCountryName(String countryName) {
		CountryName = countryName;
	}
	
	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", CountryName=" + CountryName + "]";
	}
}
