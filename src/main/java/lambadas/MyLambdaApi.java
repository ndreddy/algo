package lambadas;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MyLambdaApi {


    /**
     * 1. Accepts the lambda
     * 2. Checks whether it should be called
     * 3. Calls it when necessary
     * @param logger
     * @param message
     *
     * e.g info(() -> "x: " + x + ", y: " + y)
     */
    public static void info(Logger logger, Supplier<String> message) {
        if (logger.isLoggable(Level.INFO))
            logger.info(message.get()); //
    }

    /**
     * Repeats an action multiple times
     * @param n
     * @param action
     *
     * repeat(10, i -> System.out.println("Countdown: " + (9 - i)));
     */
    public static void repeat(int n, IntConsumer action) {
        for (int i = 0; i < n; i++) action.accept(i);
    }

    /**
     * repeat(10, () -> System.out.println("Hello, World!"));
     * @param n
     * @param action
     */
    public static void repeat(int n, Runnable action) {
        for (int i = 0; i < n; i++) action.run();
    }

    @Test
    public void ConsumerAndThen(){
        List<String> cities = Arrays.asList("Delhi", "Bangalore", "Chennai", "Hyderabad");

        Consumer<List<String>> upperCaseConsumer = list -> {
            for(int i=0; i< list.size(); i++){
                list.set(i, list.get(i).toUpperCase());
            }
        };
        Consumer<List<String>> printConsumer = list -> list.stream().forEach(System.out::println);

        upperCaseConsumer.andThen(printConsumer).accept(cities);
    }

    @Test
    public void supplierWithOptional(){
        Supplier<Double> doubleSupplier = () -> Math.random();
        Optional<Double> optionalDouble = Optional.empty();
        System.out.println(optionalDouble.orElseGet(doubleSupplier));
    }

    @Test
    public void testPredicateAndComposition(){
        List<String> names = Arrays.asList("John", "Smith", "Samueal", "Catley", "Sie");
        Predicate<String> startPredicate = str -> str.startsWith("S");
        Predicate<String> lengthPredicate = str -> str.length() >= 5;
        names.stream().filter(startPredicate.and(lengthPredicate)).forEach(System.out::println);
    }

    @Test
    public void testFunctions(){
        List<String> names = Arrays.asList("Smith", "Gourav", "Heather", "John", "Catania");
        Function<String, Integer> nameMappingFunction = String::length;
        List<Integer> nameLength = names.stream().map(nameMappingFunction).collect(Collectors.toList());
        System.out.println(nameLength);
    }
}
