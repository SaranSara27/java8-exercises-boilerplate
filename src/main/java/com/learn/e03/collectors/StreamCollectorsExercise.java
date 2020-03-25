package com.learn.e03.collectors;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learn.e02.streams.Country;
import com.learn.e02.streams.Player;

public class StreamCollectorsExercise {

	final static Logger log = LoggerFactory.getLogger(StreamCollectorsExercise.class);

	public static List<Player> playerList = Arrays
			.asList(new Player[] { new Player("Anu", 30, 3000, 79, new Country(208, "Denmark")),
					new Player("Yadhav", 102, 1000, 150, new Country(356, "India")),
					new Player("Toshi", 78, 5030, 108, new Country(524, "Nepal")),
					new Player("Barsan", 101, 5005, 99, new Country(524, "Nepal")),
					new Player("Priya", 15, 250, 50, new Country(356, "India")),
					new Player("Mithu", 111, 6789, 120, new Country(250, "France")),
					new Player("Sara", 201, 9860, 140, new Country(524, "Nepal")),

			});

	public static void main(String[] args) {

		log.debug(getPlayersByCountry().entrySet().toString());
		log.debug(getPlayerNamesByCountry().entrySet().toString());
		log.debug(getTotalPlayersByCountry().entrySet().toString());
		log.debug(getTotalRunsByCountry().entrySet().toString());
		log.debug(getMaxScoreByCountry().entrySet().toString());
		log.debug(getPlayerNamesStringByCountry().entrySet().toString());
	}

	/**
	 * Return a map with key as country name and value as List of players
	 */
	public static Map<String, List<Player>> getPlayersByCountry() {
		return playerList.stream().collect(Collectors.groupingBy(p -> p.getCountry().getCountryName()));
	}

	/**
	 * Return a map with key as country name and value as List of player Names who
	 * have played more than 100 matches
	 */
	public static Map<String, List<String>> getPlayerNamesByCountry() {
		return playerList.stream().filter(p -> p.getMatchesPlayed() > 100).collect(Collectors.groupingBy(
				p -> p.getCountry().getCountryName(), Collectors.mapping(Player::getPlayerName, Collectors.toList())));

	}

	/**
	 * Return a LinkedHashMap with key as country name and value as number of
	 * players in each country
	 */
	public static LinkedHashMap<String, Long> getTotalPlayersByCountry() {
		return playerList.stream().collect(
				Collectors.groupingBy(p -> p.getCountry().getCountryName(), LinkedHashMap::new, Collectors.counting()));

	}

	/**
	 * Return a map with key as country name and value as sum total runs of all
	 * players
	 */
	public static Map<String, Integer> getTotalRunsByCountry() {
		return playerList.stream().collect(
				Collectors.groupingBy(p -> p.getCountry().getCountryName(), Collectors.summingInt(p -> p.getRuns())));

	}

	/**
	 * Return a map with key as country name and value as maximum of the runs scored
	 * by players in that country
	 */
	public static Map<String, Optional<Player>> getMaxScoreByCountry() {

		return playerList.stream().collect(Collectors.groupingBy(p -> p.getCountry().getCountryName(),
				Collectors.maxBy(Comparator.comparing(Player::getRuns))));

	}

	/**
	 * Return a map with key as country name and value as String of player Names
	 * separated by comma
	 */
	public static Map<String, String> getPlayerNamesStringByCountry() {
		return playerList.stream().collect(Collectors.groupingBy(p -> p.getCountry().getCountryName(),
				Collectors.mapping(Player::getPlayerName, Collectors.joining(","))));

	}

}
