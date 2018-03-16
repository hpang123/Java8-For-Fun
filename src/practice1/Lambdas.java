package practice1;

import java.util.Arrays;
import java.util.Comparator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Lambdas {
	public static void main(String[] args) {
		// Old way
		String[] strings = "Mary had a little lamb".split(" ");
		Arrays.sort(strings, new LengthComparator());
		System.out.println(Arrays.toString(strings));

		//Lambda
		Comparator<String> lengthComparator = (String first, String second) -> Integer
				.compare(first.length(), second.length());

		strings = "Mary had a little lamb".split(" ");
		Arrays.sort(strings, lengthComparator);
		System.out.println(Arrays.toString(strings));
		
		// Lambda
		lengthComparator = (String first, String second) -> {
			if (first.length() < second.length()) return -1;
            else if (first.length() > second.length()) return 1;
            else return 0;
         };
         
		// omit String type if parameter type of lambda can be inferred
		// Same as (String first, String second)
		lengthComparator = (first, second) -> 
			Integer.compare(first.length(), second.length());
			
		Runnable runner = () ->{
			for (int i = 0; i < 5; i++)
				doWork();
		};
		
		new Thread(runner).start();
		
		//If parameter type can be inferred and only one parameter:
		EventHandler<ActionEvent> listener = e -> System.out.println(e
				.getTarget());
		// Instead of (e) -> or (ActionEvent e) ->
		
		//Button button = new Button("Click me!");
	    //button.setOnAction(listener);

	}

	public static void doWork() {
		System.out.println("Doing work...");
		try {
			Thread.sleep(100);
	    } catch (InterruptedException ex) {
	    	Thread.currentThread().interrupt();
	    }
	}
}

// Old way
class LengthComparator implements Comparator<String> {
	public int compare(String first, String second) {
		return Integer.compare(first.length(), second.length());
	}
}