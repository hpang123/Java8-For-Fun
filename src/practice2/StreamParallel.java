package practice2;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.function.Supplier;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

public class StreamParallel {
	public static void main(String[] args) throws IOException {
		String contents = new String(Files.readAllBytes(Paths
				.get("./src/ch2/alice.txt")), StandardCharsets.UTF_8);
		List<String> wordList = Arrays.asList(contents.split("[\\P{L}]+"));

		//reuse the stream
		Supplier<Stream<String>> supplier = () -> wordList.stream();

		// Very bad code ahead
		int[] shortWords = new int[10];
		supplier.get().parallel().forEach(s ->
			{
				if (s.length() < 10)
					shortWords[s.length()]++;
			});
		System.out.println(Arrays.toString(shortWords));

		// Try again--the result will likely be different (and also wrong)
		Arrays.fill(shortWords, 0);
		supplier.get().parallel().forEach(s ->
			{
				if (s.length() < 10)
					shortWords[s.length()]++;
			});
		System.out.println(Arrays.toString(shortWords));

		// Sequential stream works ok
		Arrays.fill(shortWords, 0);
		supplier.get().forEach(s ->
			{
				if (s.length() < 10)
					shortWords[s.length()]++;
			});
		System.out.println(Arrays.toString(shortWords));

		// Atomic integers
		AtomicInteger[] shortWordCounters = new AtomicInteger[10];
		for (int i = 0; i < shortWordCounters.length; i++)
			shortWordCounters[i] = new AtomicInteger();
		supplier.get().forEach(s ->
			{
				if (s.length() < 10)
					shortWordCounters[s.length()].getAndIncrement();
			});
		System.out.println(Arrays.toString(shortWordCounters));

		// Grouping works in parallel
		// also refer GroupingByConcurreny
		System.out.println(supplier.get().parallel()
				.filter(s -> s.length() < 10)
				.collect(groupingBy(String::length, counting())));
	}
}
