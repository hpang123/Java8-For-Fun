package practice9;

import java.nio.file.*;

public class PathDemo {
	public static void main(String[] args) {
		Path absolute = Paths.get("/", "home", "cay");
		System.out.println(absolute);
		Path relative = Paths.get("myprog", "conf", "user.properties");
		System.out.println(relative); // myprog\conf\ user.properties
		Path homeDirectory = Paths.get("/home/cay");
		System.out.println(homeDirectory);
		Path configPath = homeDirectory.resolve("myprog/conf/user.properties");
		System.out.println(configPath); //\home\cay\myprog\conf\ user.properties
		Path workPath = Paths.get("/home/cay/myprog/work");
		Path tempPath = workPath.resolveSibling("temp");
		System.out.println(tempPath); //\home\cay\myprog\temp

		System.out.println(Paths.get("/home/cay").relativize(
				Paths.get("/home/fred/myprog"))); // ..\fred\myprog
		System.out.println(Paths.get("/home/cay/../fred/./myprog").normalize()); //\home\fred\myprog

		Path p = Paths.get("/home", "cay", "myprog.properties");
		Path parent = p.getParent(); // the path /home/cay
		System.out.println(parent);
		Path file = p.getFileName(); // the path myprog.properties
		System.out.println(file);
		Path root = p.getRoot(); // the path /
		System.out.println(root);
		System.out.println(file.getRoot());

	}
}
