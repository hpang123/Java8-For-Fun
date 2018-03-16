package practice3;

import java.util.function.*;

public class Parameters {
	public static void main(String[] args) {
		repeat(10, i -> System.out.println("Countdown: " + (9 - i)));
		repeat(10, () -> System.out.println("Hello, World!"));
	}

	// IntConsumer accept int argument and return no result
	public static void repeat(int n, IntConsumer action) {
		for (int i = 0; i < n; i++)
			action.accept(i);
	}

	//Runnable functional interface no argument and no return
	public static void repeat(int n, Runnable action) {
		for (int i = 0; i < n; i++)
			action.run();
	}
}
