package practice1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FunctionalInterfaces extends Application{
	public static void main(String[] args) {  
		launch(args);
	}
	
   public void start(Stage stage) {
      String[] strings = "Mary had a little lamb".split(" ");

      Arrays.sort(strings, 
         (first, second) -> Integer.compare(first.length(), second.length()));

      System.out.println(Arrays.toString(strings));

      Button button = new Button("Click me!");
      button.setOnAction(
         event -> System.out.println("Thanks for clicking!"));

      stage.setScene(new Scene(button));
      stage.show();

      BiFunction<String, String, Integer> comp
         = (first, second) -> Integer.compare(first.length(), second.length());
         
      List<String> sortedList = Arrays.asList(strings);
      //works
      //sortedList.sort((f, s) -> Integer.compare(f.length(), s.length()));
      
      //sortedList.sort(Comparator.comparing(String::length));
      sortedList.sort(comp::apply);
      System.out.println(sortedList);
      
      
      // Arrays.sort(strings, comp); 
      // Error: Arrays.sort doesn't want a BiFunction

      //Runnable sleeper = () -> { System.out.println("Zzz"); Thread.sleep(1000); };
      // Error: Thread.sleep can throw a checked InterruptedException

      Runnable sleeper2 = () -> { 
         System.out.println("Zz"); 
         try {
        	 Thread.sleep(1000);
         } catch (InterruptedException ex) {
        	 Thread.currentThread().interrupt();
         }
      };

      new Thread(sleeper2).start();
      
      Callable<Void> sleeper3 = () -> { System.out.println("Zzz"); Thread.sleep(1000); return null; };
     
   }
}
