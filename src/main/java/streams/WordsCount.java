package streams;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

public class WordsCount {
    public static void main(String[] args) throws IOException {

        String contents = new String(Files.readAllBytes(
                Paths.get("src/main/resources/alice.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        long count = 0;

//         Instead of Iteration, use Stream Operations
        count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println(count);

/*
        1.Create: The stream method yields a stream for the words list
        2.Transform: The filter method returns another stream, of w.length() > 12
        3.Reduce: The count method reduces that stream to a result.
        Stream operations don’t mutate their source
        Stream operations are lazy when possible. they are not executed until their result is needed.
        Streams follow the “what, not how” principle
*/

        count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println(count);



//      Stream Creation: If you have an array, use the static Stream.of method instead.

        Stream<String> streamWords = Stream.of(contents.split("[\\P{L}]+"));
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        Stream<String> patterns = Pattern.compile("[\\P{L}]+").splitAsStream(contents);
        Stream<String> silence = Stream.empty(); // Generic type <String> is inferred;

        // Infinite streams
        Stream<String> echos = Stream.generate(() -> "Echo");
        Stream<Double> randoms = Stream.generate(Math::random);
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));



        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/alice.txt"))) {
            //   Do something with lines
        }

/*       Java 7 try-with-resources statement
        The stream, and the underlying file with it, will be closed
         when the try block exits normally or through an exception.
*/

//        Stream Transformation:
        Stream<String> longWords = words.stream().filter(w -> w.length() > 12);
        Stream<String> lowercaseWords = words.stream().map(String::toLowerCase);
        Stream<Character> firstChars = words.stream().map(s -> s.charAt(0));

        /*   The argument of filter is a Predicate<T>
            Use the map method and pass the function that carries out the transformation
       */

//        stream of streams - [... ['y', 'o', 'u', 'r'], ['b', 'o', 'a', 't'],...]
        Stream<Stream<Character>> result = words.stream().map(w -> characterStream(w));

//         use the flatMap - [... 'y', 'o', 'u', 'r', 'b', 'o', 'a', 't', ...]
        Stream<Character> letters = words.stream().flatMap(w -> characterStream(w));

//        Extracting Substream and combining
        Stream<Double> randoms100 = Stream.generate(Math::random).limit(100);
//        skip the first element is an unwanted empty string
        Stream<String> usefulWords = Stream.of(contents.split("[\\P{L}]+")).skip(1);
        // Yields the stream ['H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd']
        Stream<Character> combined = Stream.concat(characterStream("Hello"), characterStream("World"));

//        The peek method yields another stream with the same elements as the
//          original, but a function is invoked every time an element is retrieved
        Object[] powers = Stream.iterate(1.0, p -> p * 2)
                .peek(e -> System.out.println("Fetching " + e))
                .limit(20).toArray();

//        Stateful Transformations -  Only one "merrily" is retained
        Stream<String> uniqueWords = Stream.of("merrily", "merrily", "merrily", "gently").distinct();

//        the longest ones come first
        Stream<String> longestFirst = words.stream().sorted(Comparator.comparing(String::length).reversed());


        /* 2.6 Simple Reductions*/
        Optional<String> largest = words.stream().max(String::compareToIgnoreCase);
        if (largest.isPresent()) System.out.println("largest: " + largest.get());

        Optional<String> startsWithQFst = words.stream().filter(s -> s.startsWith("Q")).findFirst();
        Optional<String> startsWithQAny = words.stream().parallel().filter(s -> s.startsWith("Q")).findAny();
        boolean aWordStartsWithQ = words.stream().parallel().anyMatch(s -> s.startsWith("Q"));

        /* The findFirst returns the first value in a nonempty collection
        * If you are okay with any match, not just the first one, then use the findAny
        * If you just want to know there is a match, use anyMatch
        * (effective when you parallelize)
        * allMatch and noneMatch that return true if all or no elements match a predicate
        */

        /*2.7 The Optional Type*/

    }


    // characterStream("boat") is the stream ['b', 'o', 'a', 't'].
    public static Stream<Character> characterStream(String s) {
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray()) result.add(c);
        return result.stream();
    }
}
