package practice6;
import java.util.*;
import java.util.concurrent.*;

public class SetViews {
   public static void main(String[] args) {
      Set<String> words = ConcurrentHashMap.<String>newKeySet();

      ConcurrentHashMap<String, Boolean> map1 = new ConcurrentHashMap<>();
      System.out.println(map1);
      words = map1.keySet(true);
      words.add("Java");
      System.out.println(map1.get("Java"));
      
      ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
      //default value is 1 for all keys
      words = map.keySet(1L);
      words.add("Java");
      System.out.println(map.get("Java"));
      
      
   }
}
