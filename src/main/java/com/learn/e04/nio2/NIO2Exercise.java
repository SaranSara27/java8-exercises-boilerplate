package com.learn.e04.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NIO2Exercise {

	final static Logger log = LoggerFactory.getLogger(NIO2Exercise.class);

	static Path path = Paths.get("dir", "TabletDetails.csv");

	public static void main(String[] args) {

		log.debug("getExpiredTablets: {}", getExpiredTablets("TabletDetails.csv", "Apotex Corp"));
		log.debug("fetchAllfiles: {}", fetchAllfiles());

		Optional<Path> validate = search("TabletDetails.csv", path.toAbsolutePath().toString());
		if (validate.isPresent()) {
			log.debug("file present. Full path is : {}", validate.get());
		}
	}

	/**
	 * This method should return a Map with key as Tablet name and value as expire
	 * date of tablets for those tablets, which are already expired and are from a
	 * given manufacturer
	 */
	public static Map<String, String> getExpiredTablets(String filename, String manufacturer) {

		Path filePath = Paths.get("dir", filename);
		Map<String, String> mapData = NIO2Exercise.getTabletData(filePath).stream()
				.filter(t -> testDate(t.getExpiryDate()))
				.collect(Collectors.toMap(Tablet::getTabletName, Tablet::getExpiryDate));

		return mapData;

	}

	private static List<Tablet> getTabletData(Path filePath) {
		List<Tablet> tabletList = null;
		try (Stream<String> bufferData = Files.newBufferedReader(path).lines()) {
			tabletList = bufferData.map(NIO2Exercise::getTablets).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return tabletList;
	}

	private static Tablet getTablets(String data) {
		String[] input = data.split(",");
		return new Tablet(input[0], input[1], input[2], input[3]);

	}

	private static boolean testDate(String input) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate today = LocalDate.now();
		LocalDate inputDate = LocalDate.parse(input, format);
		boolean output = inputDate.isBefore(today);
		return output;
	}

	/**
	 * method to list all the files ending with .java in the current project's src
	 * folder and its subfolders
	 */
	public static List<Path> fetchAllfiles() {
		try (Stream<Path> pathObjects = Files.walk(Paths.get("src"))) {
			return pathObjects.filter(Files::isRegularFile).filter(f -> f.toString().endsWith(".java"))
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * method which takes two String parameters. The first parameter is the filename
	 * to be searched and the second parameter is the absolute path of the directory
	 * to be searched
	 */
	public static Optional<Path> search(String fileName, String dir) {
		try (Stream<Path> pathObjects = Files.walk(Paths.get(dir))) {
			return pathObjects.filter(Files::isRegularFile).filter(f -> f.getFileName().toString().equals(fileName))
					.findAny();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
