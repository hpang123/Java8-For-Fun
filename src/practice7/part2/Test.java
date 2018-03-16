package practice7.part2;

import java.io.*;
import java.nio.file.*;
import javax.script.*;

public class Test {
   public static void main(String[] args) throws ScriptException, IOException {
      ScriptEngineManager manager = new ScriptEngineManager();
      ScriptEngine engine = manager.getEngineByName("nashorn");
      Object result = (int) engine.eval("'Hello, World!'.length");
      System.out.println(result);
      result = engine.eval(Files.newBufferedReader(Paths.get("./src/ch7/sec02/hello.js")));
      System.out.println(result);
   }
}
