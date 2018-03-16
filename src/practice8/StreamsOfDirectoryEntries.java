package practice8;

import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.temporal.*;
import java.util.stream.*;

public class StreamsOfDirectoryEntries {
	public static void main(String[] args) throws IOException {
		Path pathToDirectory = Paths.get("./src/ch8");
		try (Stream<Path> entries = Files.list(pathToDirectory)) {
			entries.filter(p -> !p.getFileName().toString().startsWith("."))
					.forEach(System.out::println);
		}

		System.out.println("Hidden directories");
		try (Stream<Path> entries = Files.walk(pathToDirectory)) {
			entries.filter(p -> p.getFileName().toString().startsWith("."))
					.forEach(System.out::println);
		}

		System.out.println("Recent files");
		int depth = 5;
		Instant oneMonthAgo = Instant.now().minus(30, ChronoUnit.MINUTES);
		try (Stream<Path> entries = Files.find(
				pathToDirectory,
				depth,
				(path, attrs) -> attrs.creationTime().toInstant()
						.compareTo(oneMonthAgo) >= 0)) {
			entries.forEach(System.out::println);
		}
	}
}
