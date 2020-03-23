package com.learn.e02.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Implementation using Java 8 streams API 
 *
 */
public class StreamOperationsExercise {
	
	final static Logger log=LoggerFactory.getLogger(StreamOperationsExercise.class);
	
	
	public static List<Player> playerList= Arrays.asList(new Player[] {
			new Player("Anu", 30, 3000, 79, new Country(208, "Denmark")),
			new Player("Yadhav", 50, 1000, 150, new Country(356, "India")),
			new Player("Toshi", 78, 5030, 108, new Country(524, "Nepal")),
			new Player("Barsan", 72, 5005, 99, new Country(524, "Nepal")),
			new Player("Priya", 15, 250, 50, new Country(356, "India")),
			new Player("Mithu", 70, 6789, 120, new Country(250, "France")),
			new Player("Sara", 201, 9860, 140, new Country(524, "Nepal")),
			
	});
	
	public static void main(String[] args) {
		log.info("Names of all players:");
		displayPlayers();
		
		log.info("Players - HS>100 & belongs to India:");
		displayPlayersForCountry("India");
		
		log.info("players with runs>5000 in desc order:");
		log.debug(getPlayerNames().toString());
		
		log.info("average runs scored by players from Nepal");
		getAverageRunsByCountry("NEPAL");
		
		log.info("Players - sorted as per country and then by matchesPlayed(descending)");
		log.debug(getPlayerNamesSorted().toString());
		
		log.info("players who have played more than 200 matches");
		log.debug(getPlayerCountry().entrySet().toString());
		
		log.info("players with Max Runs:");
		log.debug(getMaxRunsPlayer().toString());
		
		log.info("Requested player details");
		log.debug(findPlayer("Sara", "Nepal").toString());
		
		log.debug("Any player scored more than 10000 runs from Nepal : {}",checkHighScorerByCountry("Nepal"));
	}
	
	/**
	 * Display the names of all players
	 */
	public static void displayPlayers() {
		playerList.stream().forEach(s->log.debug(s.getPlayerName()));
	}
	
	/**
	 * Display the name of players whose highest score is more than 100 and belong to a particular country
	 */
	public static void displayPlayersForCountry(String country) {
		playerList.stream()
		.filter(p->p.getHighestScore()>100)
		.filter(p->p.getCountry().getCountryName().equalsIgnoreCase(country))
		.forEach(p->log.debug(p.getPlayerName()));
	}
	
	/**
	 * Return a LinkedList containing names of all Players, whose have scored more than 5000 runs, sorted in descending order of names
	 * @return 
	 */
	public static LinkedList<String> getPlayerNames() {
		return playerList.stream()
				.filter(p->p.getRuns()>5000)
				.sorted((p1,p2)->p2.getPlayerName().compareTo(p1.getPlayerName()))
				.map(s->s.getPlayerName())
				.collect(Collectors.toCollection(()->new LinkedList<String>()));
		
	}
	
	/**
	 * Return the average runs scored by players from a particular Country
	 */
	public static void getAverageRunsByCountry(String country) {
		
		DoubleSummaryStatistics stats = playerList.stream()
				.filter(p->p.getCountry().getCountryName().equalsIgnoreCase(country))
				.mapToDouble(p->p.getRuns())
				.summaryStatistics();
		log.debug(""+stats.getAverage());

		
	}
	
	/**
	 * Return a list with names of Players sorted as per country and then by matchesPlayed(descending)
	 */
	public static List<String> getPlayerNamesSorted() {
		Comparator<Player> compareByName=Comparator.comparing((Player p1)->p1.getCountry().getCountryName())
        .thenComparing((Player p1)->p1.getMatchesPlayed());
		return playerList.stream()
				.sorted(compareByName)
		.map(p->p.getPlayerName())
		.collect(Collectors.toList());
	}
	
	/**
	 * return a map with the PlayerName and CountryName of all players who have played more than 200 matches
	 */
	public static Map<String,String> getPlayerCountry() {
		Map<String,String> mapData = playerList.stream()
				.filter(p->p.getMatchesPlayed()>105)
				.collect(Collectors.toMap(p->p.getPlayerName(),p->p.getCountry().getCountryName()));
		
		return mapData;

	}
	
	/**
	 * Return the player who has scored maximum runs
	 */
	public static Optional<Player> getMaxRunsPlayer() {
		return playerList.stream()
				.max((p1,p2)->p1.getRuns()-p2.getRuns());
	}
	
	/**
	 * Search and return the player for a given name and country 
	 */
	public static List<Player> findPlayer(String name, String country) {
		return playerList.stream()
		.filter(p->p.getPlayerName().equals(name))
		.filter(p->p.getCountry().getCountryName().equals(country))
		.collect(Collectors.toList());
	}
	
	/**
	 *Find whether there is any player in the given country who has scored more than 10000 runs. Return a boolean
	 */
	public static boolean checkHighScorerByCountry(String country) {
		return playerList.stream()
				.filter(p->p.getCountry().getCountryName().equals(country))
				.allMatch(s->s.getRuns()>10000)
				;

	}

}
