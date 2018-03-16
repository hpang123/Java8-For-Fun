package practice1;
import java.util.Comparator;

public class ApplicationScope {
	public static void main(String[] args) {

		// Path first = Paths.get("/usr/bin");
		// Uncomment to see error "variable first is already defined"
		// in the lambda expression below

		Comparator<String> comp = (first, second) -> Integer.compare(
				first.length(), second.length());
		
		ApplicationScope app = new ApplicationScope();
		app.doWork();
	}

	public void doWork() {

		Runnable runner = () -> {
				//this refers to application
				System.out.println(this.toString());
		};
		//Runnable has only one run abstract method, 
		//so function interface runner.run() will call lambda function
		runner.run();
		// Prints Application@... since this refers to an Application object
	}
}
