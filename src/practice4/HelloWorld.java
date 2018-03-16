package practice4;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class HelloWorld extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		Label message = new Label("Hello, JavaFX!");
		message.setFont(new Font(100));
		stage.setScene(new Scene(message));
		stage.setTitle("Hello");
		stage.show();
	}
}
