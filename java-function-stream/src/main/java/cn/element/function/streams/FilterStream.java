package cn.element.function.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/*
 * 使用filter方法
 * filter(Predicate<T> p)
 */
public class FilterStream {

    /**
     * 将一个字符串转换为字符流
     */
    public static Stream<String> codePoints(String s) {
        List<String> result = new ArrayList<>();
        
        int i = 0;
        while  (i < s.length()) {
            int j = s.offsetByCodePoints(i, 1);
            result.add(s.substring(i, j));
            i = j;
        }
        
        return result.stream();
    }

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("add");
        words.add("dog");
        words.add("cat");

        Stream<String> longWords = words.stream().filter(w -> w.length() > 1);
        Stream<String> lowerCases = words.stream().map(String::toLowerCase);

        System.out.println(longWords);
        System.out.println(lowerCases);

    }

}
