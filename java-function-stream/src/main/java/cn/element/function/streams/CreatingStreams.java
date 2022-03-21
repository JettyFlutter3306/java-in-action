package cn.element.function.streams;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/*
 * 流的创建
 * static<T> Stream<T> of(T... values)      产生一个元素给定值的流
 * static<T> Stream<T> empty()              产生一个不包含任何元素的流
 * static<T> Stream<T> generate(Supplier<? extends T> s)  产生一个无限流
 * 
 * 产生一个无限流,它的元素包含seed,在seed上调用f产生的值,在前一个元素上调用f产生的值
 * 第一个方法会产生一个无限流,而第二个则会在碰到第一个不满足hasNext谓词的元素时终止
 * 
 * static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
 * static<T> Stream<T> iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next)
 * 
 * static<T> Stream<T> ofNullable(T t)      如果t为null,返回一个空流,否则返回包含t的流
 */
public class CreatingStreams {
    
    public static <T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        List<T> firstElements = stream.limit(SIZE + 1).collect(Collectors.toList());
        System.out.println(title + ": ");

        for (int i = 0; i < firstElements.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            
            if (i < SIZE) {
                System.out.print(firstElements.get(i));
            } else {
                System.out.print("...");
            }
        }

        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        String url = "java-function-stream/src/main/resources/words.txt";
        Path path = Paths.get(url);
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        Stream<String> words = Stream.of(contents.split("\\PL+"));
        show("words", words);

        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        show("song", song);

        Stream<Object> silence = Stream.empty();
        show("silence", silence);

        Stream<String> echos = Stream.generate(() -> "Echo");
        show("echos", echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
        show("integers", integers);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
        show("wordsAnotherWay", wordsAnotherWay);
        
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            show("lines", lines);
        }

        final Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();
        final Stream<Path> rootDirectories = StreamSupport.stream(iterable.spliterator(), false);
        show("rootDirectories", rootDirectories);

        final Iterator<Path> iterator = Paths.get(url).iterator();
        final Stream<Path> pathComponents = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
        show("pathComponents", pathComponents);
    }

}
