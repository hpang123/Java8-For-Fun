package practice9;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class FilesDemo {
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("./src/ch9/FilesDemo.java");
		byte[] bytes = Files.readAllBytes(path);
		String content = new String(bytes, StandardCharsets.UTF_8);
		System.out.println(content.substring(0, 100) + "...");
		List<String> lines = Files.readAllLines(path);
		System.out.println("Last line: " + lines.get(lines.size() - 1));
		path = Paths.get("./src/ch9/FilesDemo1.out");
		content = content.replaceAll("e", "x");
		Files.write(path, content.getBytes(StandardCharsets.UTF_8));
		path = Paths.get("./src/ch9/FilesDemo2.out");
		Files.write(path, lines);
		Collections.reverse(lines);
		Files.write(path, lines, StandardOpenOption.APPEND);
		
		URL url = new URL("http://horstmann.com");

		boolean deleted = Files.deleteIfExists(Paths.get("./src/ch9/FilesDemo3.out"));
		System.out.println(deleted);

		Files.copy(url.openStream(), Paths.get("./src/ch9/FilesDemo3.out"));
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Files.copy(Paths.get("./src/ch9/FilesDemo3.out"), out);
		System.out.println(out.toString("UTF-8").substring(0, 100) + "...");

		Files.copy(Paths.get("./src/ch9/FilesDemo3.out"), Paths.get("./src/ch9/FilesDemo4.out"),
				StandardCopyOption.REPLACE_EXISTING,
				StandardCopyOption.COPY_ATTRIBUTES);
		Files.move(Paths.get("./src/ch9/FilesDemo4.out"), Paths.get("./src/ch9/FilesDemo5.out"));
		Files.copy(Paths.get("./src/ch9/FilesDemo3.out"), Paths.get("./src/ch9/FilesDemo5.out"),
				StandardCopyOption.REPLACE_EXISTING,
				StandardCopyOption.COPY_ATTRIBUTES);
		Files.move(Paths.get("./src/ch9/FilesDemo5.out"), Paths.get("./src/ch9/FilesDemo6.out"),
				StandardCopyOption.ATOMIC_MOVE);
		Files.delete(Paths.get("./src/ch9/FilesDemo6.out"));

		Path newPath = Files.createTempFile(null, ".txt");
		System.out.println(newPath); //C:\Users\Ray\AppData\Local\Temp\3739885160095704881.txt
		newPath = Files.createTempDirectory("fred"); //C:\Users\Ray\AppData\Local\Temp\fred1391053725200980879
		System.out.println(newPath);
	}
}
