package practice2;

import static java.util.stream.Collectors.counting;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByConcurrent {
	public static void main(String[] args) throws IOException {
		String contents = new String(Files.readAllBytes(Paths
				.get("./src/ch2/alice.txt")), StandardCharsets.UTF_8);
		List<String> wordList = Arrays.asList(contents.split("[\\P{L}]+"));

		Map<Integer, List<String>> result = wordList.stream().collect(
				Collectors.groupingBy(String::length));
		System.out.println(result.get(14));

		result = wordList.stream().collect(
				Collectors.groupingByConcurrent(String::length));

		System.out.println(result.get(14));

		// Collectors.groupingByConcurrent method uses a
		// shared concurrent map
		result = wordList.stream().parallel()
				.collect(Collectors.groupingByConcurrent(String::length));

		System.out.println(result.get(14));
		
		result = wordList.stream().parallel()
				.collect(Collectors.groupingByConcurrent(String::length));
		
		Map<Integer, Integer> lengthCount = result.entrySet().stream()
				.collect(Collectors.toMap(e -> e.getKey(), e-> e.getValue().size()));
		System.out.println(lengthCount.get(14));
		
		//refer StreamParallel
		System.out.println(wordList.stream().parallel()
				.filter(s -> s.length() > 10)
				.collect(Collectors.groupingByConcurrent(String::length, counting())).get(14));
	}
}
