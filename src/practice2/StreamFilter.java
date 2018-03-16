package practice2;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.*;

public class StreamFilter
{
    public static void main(String[] args) throws IOException
    {
        String contents = new String(Files.readAllBytes(
                Paths.get("./src/ch2/war-and-peace.txt")), StandardCharsets.UTF_8);
        // Split into words; nonletters are delimiters
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        System.out.println("Total words:" + words.size());

        long count = 0;
        for (String w : words) {
           if (w.length() > 12) count++;  
        }
        System.out.println(count);

        Instant start = Instant.now();
        count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println(count);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        

        Instant start2 = Instant.now();
        count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println(count);
        Instant end2 = Instant.now();
        Duration timeElapsed2 = Duration.between(start2, end2);
        
        boolean Faster = timeElapsed2.minus(timeElapsed).isNegative();
        System.out.printf("The second algorithm is %sfaster than the first one:(%s millsec)\n", Faster ? "" : "not ", timeElapsed2.minus(timeElapsed).toMillis());
        
        List<String> longwords = words.parallelStream().filter(w -> w.length() > 12).collect(Collectors.toList());
        words.parallelStream().filter(w -> w.length() > 12).forEach(System.out::println);
    }
}
