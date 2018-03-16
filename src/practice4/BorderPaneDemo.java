package practice4;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class BorderPaneDemo extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		BorderPane pane = new BorderPane();
		pane.setTop(new Button("Top"));
		pane.setLeft(new Button("Left"));
		pane.setCenter(new Button("Center"));
		pane.setRight(new Button("Right"));
		pane.setBottom(new Button("Bottom"));
		stage.setScene(new Scene(pane));
		stage.show();
	}
}
