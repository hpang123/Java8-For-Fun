package practice2;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;


public class SimpleReduction extends TestBase {
	public static void main(String[] args) throws IOException {
		String contents = new String(Files.readAllBytes(Paths
				.get("./src/ch2/alice.txt")), StandardCharsets.UTF_8);
		List<String> wordList = Arrays.asList(contents.split("[\\P{L}]+"));

		Stream<String> words = wordList.stream().filter(w -> w.length() > 0);

		Optional<String> largest = words.max(String::compareToIgnoreCase);
		if (largest.isPresent())
			System.out.println("largest: " + largest.get());
		
		largest.ifPresent(s -> System.out.println("ifPresent largest: " + s));
		
		words = Stream.empty();
		largest = words.max(String::compareToIgnoreCase);
		if (!largest.isPresent())
			System.out.println("largest is not present" );
		
		largest.ifPresent(s -> System.out.println("ifPresent largest: " + s));

		words = wordList.stream();
		boolean aWordStartsWithQ = words.anyMatch(s -> s.startsWith("Q"));
		System.out.println("aWordStartsWithQ: " + aWordStartsWithQ);
		
		words = wordList.stream();
		//boolean noneMatchWord = words.noneMatch(s -> s.equalsIgnoreCase("quick1"));
		boolean noneMatchWord = words.noneMatch("quick1"::equalsIgnoreCase);
		System.out.println("noneMatchWord: " + noneMatchWord);

		words = wordList.stream();
		Optional<String> startsWithQ = words.parallel()
				.filter(s -> s.startsWith("Q")).findAny();
		if (startsWithQ.isPresent())
			System.out.println("startsWithQ: " + startsWithQ.get());
		else
			System.out.println("No word starts with Q");
		
		words = wordList.stream();
		Optional<String> firstStartsWithQ = words.filter(s -> s.startsWith("Q")).findFirst();
		if (firstStartsWithQ.isPresent())
			System.out.println("firstStartsWithQ: " + firstStartsWithQ.get());
		else
			System.out.println("No word starts with Q");
		
		System.out.println(firstStartsWithQ.orElse("No word"));
		
	}
}
