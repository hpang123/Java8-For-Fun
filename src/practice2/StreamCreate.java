package practice2;

import java.io.*;
import java.math.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class StreamCreate extends TestBase{

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("./src/ch2/war-and-peace.txt");
		String contents = new String(Files.readAllBytes(path),
				StandardCharsets.UTF_8);

		//example for create stream from array
		Stream<String> words = Stream.of(contents.split("[\\P{L}]+"));
		show("words", words);
		
		words = Arrays.stream(contents.split("[\\P{L}]+"));
		show("arrays words", words);
		
		//of method has a varargs parameter
		Stream<String> song = Stream.of("gently", "down", "the", "stream");
		show("song", song);
		
		//To make a stream with no elements
		Stream<String> silence = Stream.empty();
		silence = Stream.<String> empty(); // Explicit type specification
		show("silence", silence);
		
		//To create infinite streams, use generate method takes a function with no arguments
		//Supplier<T> interface - functional interface
		//Whenever a stream value is needed, that function is called 
		//to produce a value, if limit 11, it will call 11 times
		Stream<String> echos = Stream.generate(() -> "Echo");
		show("echos", echos);

		Stream<Double> randoms = Stream.generate(Math::random);
		show("randoms", randoms);
		
		//use the iterate method that takes a “seed” value
		//and a function (technically, a UnaryOperator<T>), and
		//repeatedly applies the function to the previous result.
		Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE,
				n -> n.add(BigInteger.ONE));
		show("integers", integers);
		
		Stream<Integer> ints = Stream.iterate(1, n -> n+1);
		show("ints", ints);
		
		//For primitive type
		IntStream.range(1, 11)
	    .forEach(System.out::println);
		
		IntStream intStream = IntStream.range(1, 11);
		int[] intArray =intStream.limit(10).toArray();
		System.out.println(Arrays.toString(intArray));
		
		//create stream from pattern
		Stream<String> wordsAnotherWay = Pattern.compile("[\\P{L}]+")
				.splitAsStream(contents);
		show("wordsAnotherWay", wordsAnotherWay);

		//The static Files.lines method returns a Stream of all lines in a file. The Stream
		//interface has AutoCloseable as a superinterface.When the close method is
		//called on the stream, the underlying file is also closed
		try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
			show("lines", lines);
		}
		
	}
}
