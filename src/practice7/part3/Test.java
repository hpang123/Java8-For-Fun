package practice7.part3;

import java.io.*;
import java.nio.file.*;

import javafx.application.*;
import javafx.stage.*;

import javax.script.*;

public class Test extends Application {
	private static ScriptEngineManager manager = new ScriptEngineManager();
	private static ScriptEngine engine = manager.getEngineByName("nashorn");

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		Bindings scope = engine.createBindings();
		scope.put("stage", stage);
		try {
			engine.eval(Files.newBufferedReader(Paths
					.get("./src/ch7/sec03/hellofx.js")), scope);
			// Script code can access the object as stage
		} catch (IOException | ScriptException ex) {
			ex.printStackTrace();
		}
	}
}
