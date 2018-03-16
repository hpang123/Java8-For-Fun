package practice2;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;


import java.util.stream.*;

public class StreamReduce {
	public static void main(String[] args) throws IOException {
		Integer[] digits = { 3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9, 3, 2,
				3, 8, 4, 6, 2, 6, 4, 3, 3, 8, 3, 2, 7, 9, 5, 0, 2, 8, 8, 4, 1,
				9, 7, 1, 6, 9, 3, 9, 9, 3, 7, 5, 1, 0, 5, 8, 2, 0, 9, 7, 4, 9,
				4, 4, 5, 9, 2, 3, 0, 7, 8, 1, 6, 4, 0, 6, 2, 8, 6 };

		Stream<Integer> values = Stream.of(digits);

		/*
		 * List<Integer> l = new ArrayList<Integer>(); l.add(new Integer(3));
		 * l.add(new Integer(1));
		 * 
		 * 
		 * 
		 * Stream<Integer> i = l.stream();
		 * System.out.println(l.stream().mapToInt(Integer::intValue).average());
		 */

		//It requires a function (T, T) -> T( x,y, and x+y are same type)
		Optional<Integer> sum = values.reduce((x, y) -> x + y);

		System.out.println("sum: " + sum);
		System.out.println("average: "
				+ Stream.of(digits).mapToInt(Integer::intValue).average());

		values = Stream.empty();
		sum = values.reduce((x, y) -> x + y); // Or values.reduce(Integer::sum);
		System.out.println("sum: " + sum); // Optional.empty

		values = Stream.of(digits);
		Integer sum2 = values.reduce(0, (x, y) -> x + y); // return Integer
															// since it has
															// identity
		System.out.println("sum2: " + sum2);

		// The identity value is returned if the stream is empty, and you no
		// longer need to
		// deal with the Optional class.
		values = Stream.empty();
		Integer sum3 = values.reduce(0, (x, y) -> x + y); // 0 Integer
		System.out.println("sum3: " + sum3);

		String contents = new String(Files.readAllBytes(Paths
				.get("./src/ch2/alice.txt")), StandardCharsets.UTF_8);
		List<String> wordList = Arrays.asList(contents.split("[\\P{L}]+"));
		Stream<String> words = wordList.stream();

		//Identity value, BiFunction accumulater and combiner function of type
		int result = words.reduce(0, (s, w) -> s + w.length(), (s1, s2) -> s1 + s2);

		//int result = words.reduce(0, (s, w) -> s += w.length(), (s1, s2) -> s1 + s2);
		//int result = words.mapToInt(String::length).reduce(0, (x, y) -> x+ y);
		//int result = words.mapToInt(String::length).sum();

		System.out.println("result: " + result);
	}
}
