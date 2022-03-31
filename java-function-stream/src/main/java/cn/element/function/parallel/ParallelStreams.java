package cn.element.function.parallel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class ParallelStreams {

    public static void main(String[] args) throws IOException {
        String url = "java-function-stream/src/main/resources/words.txt";
        String contents = new String(Files.readAllBytes(Paths.get(url)), StandardCharsets.UTF_8);
        List<String> wordList = List.of(contents.split("\\PL+"));
        
        int[] shortWords = new int[8];
        wordList.parallelStream().forEach(s -> {
            if (s.length() < 8) {
                shortWords[s.length()]++;
            }
        });
        System.out.println(Arrays.toString(shortWords));
        
        Arrays.fill(shortWords, 0);
        wordList.parallelStream().forEach(s -> {
            if (s.length() < 8) {
                shortWords[s.length()]++;
            }
        });
        System.out.println(Arrays.toString(shortWords));

        Map<Integer, Long> shotWordCounts = wordList.parallelStream()
                                                    .filter(s -> s.length() < 8)
                                                    .collect(groupingBy(String::length, counting()));
        System.out.println(shotWordCounts);


        Map<Integer, List<String>> result = wordList.parallelStream()
                                                    .collect(groupingByConcurrent(String::length));
        System.out.println(result.get(7));
        
        result = wordList.parallelStream().collect(groupingByConcurrent(String::length));
        System.out.println(result.get(7));

        Map<Integer, Long> wordCounts = wordList.parallelStream()
                                                .collect(groupingByConcurrent(String::length, counting()));
        System.out.println(wordCounts);
    }
}
