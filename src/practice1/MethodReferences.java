package practice1;

import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MethodReferences extends Application {
	public static void main(String[] args) {  
		launch(args);
	}
	
	public void start(Stage stage) {
		String[] strings = "Mary had a little lamb".split(" ");
		//It is same: Arrays.sort(strings, (x, y) -> x.compareToIgnoreCase(y));
		Arrays.sort(strings, String::compareToIgnoreCase);
		
		System.out.println(Arrays.toString(strings));

		Button button = new Button("Click me!");
		
		//It is same: x -> System.out.println(x)
		button.setOnAction(System.out::println);
		stage.setScene(new Scene(button));
		stage.show();
	}
}
