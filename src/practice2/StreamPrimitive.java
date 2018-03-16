package practice2;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamPrimitive {
	public static void show(String title, IntStream stream) {
		final int SIZE = 10;
		// toArray methods return primitive type arrays.
		int[] firstElements = stream.limit(SIZE + 1).toArray();
		System.out.print(title + ": [");
		int i;
		for (i = 0; i < SIZE && i < firstElements.length; i++) {
			System.out.print(firstElements[i]);
			if (i < firstElements.length - 1)
				System.out.print(", ");
		}
		if (i < firstElements.length)
			System.out.print("...");
		System.out.println("]");
	}

	public static void main(String[] args) throws IOException {
		IntStream is1 = IntStream.generate(() -> (int) (Math.random() * 100));
		show("is1", is1);

		IntStream intStream = new Random().ints(0, 100);
		show("intStream", intStream);

		DoubleStream doubleStream = DoubleStream
				.generate(() -> (Math.random() * 100));
		System.out.println("doubleValue:"
				+ Arrays.toString(doubleStream.limit(11).toArray()));

		IntStream is2 = IntStream.range(5, 10);
		show("is2", is2);
		IntStream is3 = IntStream.rangeClosed(5, 10);
		show("is3", is3);

		Path path = Paths.get("./src/ch2/alice.txt");
		String contents = new String(Files.readAllBytes(path),
				StandardCharsets.UTF_8);

		Stream<String> words = Stream.of(contents.split("[\\P{L}]+"));
		IntStream is4 = words.mapToInt(String::length);
		show("is4", is4);
		
		String sentence = "\uD835\uDD46 is the set of octonions.";
		System.out.println(sentence);
		IntStream codes = sentence.codePoints();
		System.out.println(codes.mapToObj(c -> String.format("%X ", c))
				.collect(Collectors.joining()));

		IntStream.range(1, 4)
	    .mapToObj(i -> "a" + i)
	    .forEach(System.out::print);
		System.out.println();

		codes = sentence.codePoints();
		System.out.println(Arrays.toString(codes.toArray()));
		
		codes = sentence.codePoints();
		System.out.println(codes.mapToObj(String::valueOf).collect(Collectors.toList()));
		
		Stream<Integer> integers = IntStream.range(0, 100).boxed();
		IntStream is5 = integers.mapToInt(Integer::intValue);
		show("is5", is5);
	}
}
