package practice7.part2;

import java.io.*;
import java.nio.file.*;

import javafx.application.*;
import javafx.stage.*;

import javax.script.*;

public class Test2 extends Application {
	private static ScriptEngineManager manager = new ScriptEngineManager();
	private static ScriptEngine engine = manager.getEngineByName("nashorn");

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {

		engine.put("stage", stage);
		try {
			engine.eval(Files.newBufferedReader(Paths.get("./src/ch7/sec02/hellofx.js")));
			// Script code can access the object as stage
		} catch (IOException | ScriptException ex) {
			ex.printStackTrace();
		}
	}
}
