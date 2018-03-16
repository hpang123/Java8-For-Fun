package practice9;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.*;

public class ProcessDemo {
	public static void main(String[] args) throws IOException,
			InterruptedException {
		ProcessBuilder builder = new ProcessBuilder("grep", "-o",
				"[A-Za-z_][A-Za-z_0-9]*");
		
		Files.copy(Paths.get("./src/ch9/ProcessDemo.java"), Paths.get("./src/ch9/ProcessDemo.out"),
				StandardCopyOption.REPLACE_EXISTING,
				StandardCopyOption.COPY_ATTRIBUTES);
		
		builder.redirectInput(Paths.get("./src/ch9/ProcessDemo.java").toFile());
		builder.redirectOutput(Paths.get("./src/ch9/identifiers.txt").toFile());
		Process process = builder.start();
		process.waitFor(1, TimeUnit.MINUTES);
	}
}
