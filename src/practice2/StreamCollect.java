package practice2;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class StreamCollect {
	public static Stream<String> noVowels(String filename) throws IOException {
		String contents = new String(Files.readAllBytes(Paths.get(filename)),
				StandardCharsets.UTF_8);
		List<String> wordList = Arrays.asList(contents.split("[\\P{L}]+"));
		Stream<String> words = wordList.stream();
		return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
	}

	public static <T> void show(String label, Set<T> set) {
		System.out.print(label + ": " + set.getClass().getName());
		System.out.println("["
				+ set.stream().limit(10).map(Object::toString)
						.collect(Collectors.joining(", ")) + "]");
	}

	public static void main(String[] args) throws IOException {

		//convert to iterator 
		Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10)
				.iterator();
		while (iter.hasNext())
			System.out.print(iter.next() + " ");
		
		System.out.println();
		
		Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
		System.out.println(Arrays.toString(numbers)); // Note it's an Object[] array

		try {
			Integer number = (Integer) numbers[0]; // OK
			System.out.println("number: " + number);
			Integer[] numbers2 = (Integer[]) numbers; // Throws exception
		} catch (ClassCastException ex) {
			ex.printStackTrace();
		}

		//If you want an array of the correct type,
		//pass in the array constructor
		Integer[] numbers3 = Stream.iterate(0, n -> n + 1).limit(10)
				.toArray(Integer[]::new);
		System.out.println(numbers3); // Note it's an Integer[] array

		//supplier, accumulator, combiner
		HashSet<String> noVowelHashSet = noVowels("./src/ch2/alice.txt")
				.collect(HashSet::new, HashSet::add, HashSet::addAll);

		show("noVowelHashSet", noVowelHashSet);

		Set<String> noVowelSet = noVowels("./src/ch2/alice.txt").collect(
				Collectors.toSet());
		show("noVowelSet", noVowelSet);

		//If you want to control which kind of set you get
		TreeSet<String> noVowelTreeSet = noVowels("./src/ch2/alice.txt")
				.collect(Collectors.toCollection(TreeSet::new));
		show("noVowelTreeSet", noVowelTreeSet);

		//by concatenating
		String result = noVowels("./src/ch2/alice.txt").limit(10).collect(
				Collectors.joining());
		System.out.println(result);
		
		result = noVowels("./src/ch2/alice.txt").limit(10).collect(
				Collectors.joining(", "));
		System.out.println(result);

		
		IntSummaryStatistics summary = noVowels("./src/ch2/alice.txt").collect(
				Collectors.summarizingInt(String::length));
		double averageWordLength = summary.getAverage();
		double maxWordLength = summary.getMax();
		summary.getSum();
		System.out.println("Average word length: " + averageWordLength);
		System.out.println("Max word length: " + maxWordLength);
		System.out.println("Sum of length: " + summary.getSum());
		
		noVowels("./src/ch2/alice.txt").limit(10).forEach(System.out::println);
	}
}
