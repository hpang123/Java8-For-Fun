package practice3;

import java.util.function.*;
import java.util.concurrent.*;
import java.nio.file.*;
import java.nio.charset.*;

public class ExceptionDemo2 {

	public static <T> Supplier<T> unchecked(Callable<T> f) {
		return () ->
			{
				//Callable requires check exception: 
				//declared as T call() throws Exception
				try {
					return f.call();
				} catch (Exception e) {
					throw new RuntimeException(e);
				} catch (Throwable t) {
					throw t;
				}
			};
	}

	public static void main(String[] args) {
		
		Supplier<String> s = unchecked(() -> new String(
				Files.readAllBytes(Paths.get("/etc/passwd")),
				StandardCharsets.UTF_8));
		
		//Supplier<String> s = unchecked(() -> new String("test"));
		System.out.println(s.get());
	}
}
