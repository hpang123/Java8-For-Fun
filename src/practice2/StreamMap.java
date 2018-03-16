package practice2;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class StreamMap extends TestBase{

   public static void main(String[] args) throws IOException {
      String contents = new String(Files.readAllBytes(
            Paths.get("./src/ch2/war-and-peace.txt")), StandardCharsets.UTF_8);
      List<String> wordList = Arrays.asList(contents.split("[\\P{L}]+"));
      Stream<String> words = wordList.stream();
      Stream<String> longWords = words.filter(w -> w.length() > 12);
      show("longWords", longWords);

      words = wordList.stream();
      Stream<String> lowercaseWords = words.map(String::toLowerCase);
      show("lowercaseWords", lowercaseWords);

      Stream<String> song = Stream.of("row", "row", "row", "your", "boat", "gently", "down", "the", "stream");
      Stream<Character> firstChars = song.filter(w -> w.length() > 0).map(s -> s.charAt(0));
      show("firstChars", firstChars);

      
      song = Stream.of("row", "row", "row", "your", "boat", "gently", "down", "the", "stream");
      Stream<Character> letters = song.flatMap(w -> characterStream(w));
      show("letters", letters);
      
      song = Stream.of("row", "row", "row", "your", "boat", "gently", "down", "the", "stream");
      Stream<Stream<Character>> streamletters = song.map(w -> characterStream(w));;
      
   }
}
