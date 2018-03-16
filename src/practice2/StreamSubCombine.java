package practice2;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class StreamSubCombine extends TestBase {

	public static void main(String[] args) throws IOException {
		Stream<Double> randoms = Stream.generate(Math::random).limit(100);
		show("randoms", randoms);

		Stream<Integer> integers = Stream.iterate(0, n -> n + 1);
		Stream<Integer> firstFive = integers.limit(5);
		show("firstFive", firstFive);

		integers = Stream.iterate(0, n -> n + 1);
		Stream<Integer> notTheFirst = integers.skip(1);
		show("notTheFirst", notTheFirst);

		Stream<Character> combined = Stream.concat(characterStream("Hello"),
				characterStream("World"));
		show("combined", combined);

		//Stream.toArray returns Object[]
		Object[] powers = Stream.iterate(1.0, p -> p * 2)
				.peek(e -> System.out.println("Fetching " + e)).limit(10)
				.toArray();
		System.out.println(Arrays.asList(powers));
	}
}

