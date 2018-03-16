package practice3;

import java.util.function.*;
import java.util.logging.*;

public class DeferredLogging {
	//Supplier no parameter and return T
	public static void info(Logger logger, Supplier<String> message) {
		if (logger.isLoggable(Level.INFO) ){
			// Supplier.get will run the function and return the result
			System.out.println("logger info:" + logger.getLevel());
			logger.info(message.get());
			
		}
	}

	public static void main(String[] args) {
		
		/*
		Logger log = LogManager.getLogManager().getLogger("");
		for (Handler h : log.getHandlers()) {
		    h.setLevel(Level.SEVERE);
		}
		*/
		
		Logger.getGlobal().setLevel(Level.INFO);
		double x = 3;
		double y = 4;
		//Deferred Execution
		info(Logger.getGlobal(), () -> "x: " + x + ", y: " + y);
		// pass Supply
		Logger.getGlobal().info(() -> "x: " + x + ", y: " + y);
		//Not Deferred Execution
		Logger.getGlobal().info("x: " + x + ", y: " + y);
		
	}
}
