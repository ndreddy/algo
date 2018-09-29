package lambadas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Comparator;
import java.util.concurrent.Callable;


public class Lambdas {
    public static void main(String[] args) {

        /*
         * A lambda expression is a block of code that you can pass around so it can be
         * executed later, once or multiple times.
         *
         * A block of code was passed to someone—a thread pool, a sort method, or a button.
         * The code was called at some later time.
         */

        // 1. lambda can only be assignable to FunctionalInterface.
        Comparator<String> c = (f, s) -> Integer.compare(f.length(), s.length());
//        Comparator<String> c = Comparator.comparingInt(String::length);

        Runnable r = () -> { for (int i = 0; i < 1000; i++) doWork();};

        // You can't do this with Runnable as Thread.sleep throws exception
        Callable<Void> sleep = () -> {Thread.sleep(1000); return null;};

        EventHandler<ActionEvent> listener = e -> System.out.println(e.getTarget());

        // Instead of (e) -> or (ActionEvent e) ->
        //  map.forEach((key, value) -> System.out.println(key + ":" + value));
//        messages.forEach((k, v) -> executor.submit(() -> processMessage(k, v)));
    }

    public static void doWork() {
        System.out.println("Lambda");
    }
}
