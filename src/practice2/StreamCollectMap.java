package practice2;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class Person {
	private int id;
	private String name;

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return getClass().getName() + "[id=" + id + ",name=" + name + "]";
	}
}

public class StreamCollectMap {
	public static Stream<Person> people() {
		return Stream.of(new Person(1001, "Peter"), new Person(1002, "Paul"),
				new Person(1003, "Mary"));
	}

	public static Stream<Person> peopleDuplicate() {
		return Stream.of(new Person(1001, "Peter"), new Person(1002, "Paul"),
				new Person(1003, "Mary"), new Person(1003, "Mary1"));
	}

	public static void main(String[] args) throws IOException {
      Map<Integer, String> idToName = people().collect(Collectors.toMap(Person::getId, Person::getName));
      System.out.println("idToName: " + idToName);

      Map<Integer, Person> idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity()));
      System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);

      idToPerson = people().collect(
         Collectors.toMap(
            Person::getId,
            Function.identity(),
            (existingValue, newValue) -> { throw new IllegalStateException(); },
            TreeMap::new));

      System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);
      
      idToPerson = peopleDuplicate().collect(
    	         Collectors.toMap(
    	            Person::getId,
    	            Function.identity(),
    	            (existingValue, newValue) -> existingValue, // keep old key value
    	            TreeMap::new));

      System.out.println("duplicate idToPerson: " + idToPerson.getClass().getName() + idToPerson);
    	      
      //It will throw exception: Duplicate key
      try {
    	  idToPerson = peopleDuplicate().collect(
    	         Collectors.toMap(
    	            Person::getId,
    	            Function.identity()
    	            ));
    	  System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);

      }
      catch(IllegalStateException e){
    	  e.printStackTrace();
      }    
    	      

      Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
      Locale[] localesArray = Locale.getAvailableLocales();
      Locale locale = localesArray[0];
      if(locale == null){
    	  System.out.println("locale is null");
      }
      String language = locale.getDisplayLanguage();
      
      
     
      
      Map<String, Locale> countryNames = locales.collect(
         Collectors.toMap(
            Locale::getCountry,
            Function.identity(),
            (existingValue, newValue) -> existingValue));
      
      System.out.println("CountryNames: " + countryNames);
      
      locales = Stream.of(Locale.getAvailableLocales());
      Map<String, String> countryNameString = locales.collect(
    	         Collectors.toMap(
    	            Locale::getCountry,
    	            Locale::getCountry,
    	            (existingValue, newValue) -> existingValue));
    	      
      System.out.println("countryNameString: " + countryNameString);
      

      locales = Stream.of(Locale.getAvailableLocales()).filter(s ->s.getDisplayCountry().length() >0);
      Map<String, Set<String>> countryLanguageSets = locales.collect(
         Collectors.toMap(
            s -> s.getDisplayCountry(),
            l -> Collections.singleton(l.getDisplayLanguage()),
            (a, b) -> { // union of a and b
               Set<String> r = new HashSet<>(a); 
               r.addAll(b);
               return r; }));
      System.out.println("countryLanguageSets: " + countryLanguageSets);
      
   }
}
