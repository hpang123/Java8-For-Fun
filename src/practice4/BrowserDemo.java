package practice4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class BrowserDemo extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		//String location = "http://horstmann.com";
		String location = "http://www.google.com";
		WebView browser = new WebView();
		WebEngine engine = browser.getEngine();
		engine.load(location);
		Scene scene = new Scene(browser);
		stage.setScene(scene);
		stage.setWidth(500);
		stage.setHeight(500);
		stage.show();
	}

}
