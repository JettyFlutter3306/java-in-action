package cn.element.function.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * 产生流
 */
public class CountWords {

    /**
     * 统计长度大于8的单词数
     */
    public static void main(String[] args) throws IOException {
        String path = "java-function-stream/src/main/resources/words.txt";
        String contents = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        List<String> words = List.of(contents.split("\\PL+"));
        
        long count = 0;
        for (String word : words) {
            if (word.length() > 8) {
                count++;
            }
        }

        System.out.println(count);
        
        long count1 = words.stream().filter(w -> w.length() > 8).count();
        System.out.println(count1);
        
        long count2 = words.parallelStream().filter(w -> w.length() > 8).count();
        System.out.println(count2);
    }

}
