package practice2;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class StreamState extends TestBase {
 
   public static void main(String[] args) throws IOException {
      Stream<String> uniqueWords
         = Stream.of("merrily", "merrily", "merrily", "gently").distinct();
      
      show("uniqueWords", uniqueWords);

      String contents = new String(Files.readAllBytes(
            Paths.get("./src/ch2/war-and-peace.txt")), StandardCharsets.UTF_8);
      List<String> wordList = Arrays.asList(contents.split("[\\P{L}]+"));
      Stream<String> words = wordList.stream();
      show("words", words);

      words = wordList.stream();
      Stream<String> distinct = words.distinct();
      show("distinct", distinct);

      words = wordList.stream();
      Stream<String> sorted = words.sorted();
      show("sorted", sorted);

      words = wordList.stream();
      Stream<String> distinctSorted = words.distinct().sorted();
      show("distinctSorted", distinctSorted);

      words = wordList.stream();
      Stream<String> longestFirst = words.sorted(Comparator.comparing(String::length).reversed());
      show("longestFirst", longestFirst);
   }
}