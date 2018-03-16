package practice2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestBase {

	// Generic method
	public static <T> void show(String title, Stream<T> stream) {
		final int SIZE = 10;
		List<T> firstElements = stream.limit(SIZE + 1).collect(
				Collectors.toList());
		System.out.print(title + ": ");
		if (firstElements.size() <= SIZE)
			System.out.println(firstElements);
		else {
			// remove the last(10) string since there are 11 elements
			firstElements.remove(SIZE);
			String out = firstElements.toString();
			// length() -1 to remove the ] at the end
			System.out.println(out.substring(0, out.length() - 1) + ", ...]");
		}
	}

	public static Stream<Character> characterStream(String s) {
		List<Character> result = new ArrayList<>();
		for (char c : s.toCharArray())
			result.add(c);
		return result.stream();
	}
}
