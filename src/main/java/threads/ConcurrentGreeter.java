package threads;

/**
 * A subclass should call its super class version of method in a seperate thread.
 */
class Greeter {
    public void greet() {
        System.out.println("Hello, world!");
    }
}

public class ConcurrentGreeter extends Greeter {
    public void greet() {
        Thread t = new Thread(super::greet);
        t.start();
    }
}