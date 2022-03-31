package cn.element.function.collecting;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingResults {
    
    public static Stream<String> noVowels() throws IOException {
        String url = "java-function-stream/src/main/resources/words.txt";
        String contents = new String(Files.readAllBytes(Paths.get(url)), StandardCharsets.UTF_8);
        List<String> wordList = List.of(contents.split("\\PL+"));
        Stream<String> words = wordList.stream();
        return words.map(w -> w.replaceAll("[aeiouAEIOU]", ""));
    }
    
    public static <T> void show(String label, Set<T> set) {
        System.out.println(label + ": " + set.getClass().getName());
        System.out.println("[" + set.stream()
                                    .limit(10)
                                    .map(Objects::toString)
                                    .collect(Collectors.joining(", ")) + "]"
        );
    }

    public static void main(String[] args) throws IOException {
        Iterator<Integer> it = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println("============================");
        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        System.out.println("Object array: " + Arrays.toString(numbers));

        try {
            Integer number = (Integer) numbers[0];
            System.out.println("number: " + number);
            System.out.println("The following statement throws an exception:");
            Integer[] number2 = (Integer[]) numbers;
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("============================");
        Integer[] number3 = Stream.iterate(0, n -> n + 1)
                                  .limit(10)
                                  .toArray(Integer[]::new);
        System.out.println("Integer array: " + Arrays.toString(number3));

        System.out.println("============================");
        Set<String> set = noVowels().collect(Collectors.toSet());
        show("noVowelSet", set);
        
        TreeSet<String> treeSet = noVowels().collect(Collectors.toCollection(TreeSet::new));
        show("noVowelTreeSet", treeSet);

        System.out.println("============================");
        String result = noVowels().limit(10).collect(Collectors.joining());
        System.out.println("Joining: " + result);
        result = noVowels().limit(10).collect(Collectors.joining(", "));
        System.out.println("Joining with commas: " + result);

        System.out.println("============================");
        IntSummaryStatistics summary = noVowels().collect(Collectors.summarizingInt(String::length));
        double average = summary.getAverage();
        int max = summary.getMax();
        System.out.println("Average word length: " + average);
        System.out.println("Max word length: " + max);
        System.out.println("forEach: ");
        noVowels().limit(10).forEach(System.out::println);
    }

}
