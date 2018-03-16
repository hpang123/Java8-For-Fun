package practice6;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;
import java.util.stream.Stream;

public class ParallelArrayOps {
	public static void main(String[] args) throws IOException {
		String contents = new String(Files.readAllBytes(Paths
				.get("./src/ch2/alice.txt")), StandardCharsets.UTF_8); // Read
																		// file
																		// into
																		// string
		//String[] words = contents.split("[\\P{L}]"); // Split along nonletters
		String[] words = Arrays.stream(contents.split("[\\P{L}]+"))
				.filter(w -> w.length() > 0).limit(100)
				//.filter(Objects::nonNull).limit(100)
				//.filter(w ->  !w.equals("")).limit(100)
				.toArray(String[]::new);
		
		Arrays.parallelSort(words);
		System.out.println(Arrays.toString(words));

		int[] values = new int[20];
		Arrays.parallelSetAll(values, i -> i % 10);
		System.out.println(Arrays.toString(values));
		
		Arrays.parallelSort(values);
		System.out.println(Arrays.toString(values));

		Arrays.parallelSetAll(values, i -> i + 1);
		System.out.println(Arrays.toString(values));
		
		Arrays.parallelPrefix(values, (x, y) -> x * y);
		System.out.println(Arrays.toString(values));
	}
}
