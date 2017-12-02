package threads;

import algo.AlgoUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static algo.AlgoUtil.randomIntInRange;

class Chopstick {
    private Lock lock;

    int id;

    public Chopstick(int n) {
        lock = new ReentrantLock();
        this.id = n;
    }

    public boolean pickUp() {
        return lock.tryLock();
    }

    public void putDown() {
        lock.unlock();
    }
}

public class Philosopher extends Thread {
    private final int maxPause = 100;
    private int bites = 10;

    private Chopstick lower;
    private Chopstick higher;
    private int index;
    public Philosopher(int i, Chopstick left, Chopstick right) {
        index = i;
        if (left.id < right.id) {
            this.lower = left;
            this.higher = right;
        } else {
            this.lower = right;
            this.higher = left;
        }
    }

    public void eat() {
        System.out.println("Philosopher " + index + ": start eating");
        pickUp();
        chew();
        putDown();
        System.out.println("Philosopher " + index + ": done eating");
    }

    public void pickUp() {
        pause();
        lower.pickUp();
        pause();
        higher.pickUp();
        pause();
    }

    public void chew() {
        System.out.println("Philosopher " + index + ": eating");
        pause();
    }

    public void pause() {
        try {
            int pause = AlgoUtil.randomIntInRange(0, maxPause);
            Thread.sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void putDown() {
        higher.putDown();
        lower.putDown();
    }

    public void run() {
        for (int i = 0; i < bites; i++) {
            eat();
        }
    }
}
class TestPhilosophers {
    public static int size = 3;

    public static int leftOf(int i) {
        return i;
    }

    public static int rightOf(int i) {
        return (i + 1) % size;
    }

    public static void main(String[] args) {
        Chopstick[] chopsticks = new Chopstick[size + 1];
        for (int i = 0; i < size + 1; i++) {
            chopsticks[i] = new Chopstick(i);
        }

        Philosopher[] philosophers = new Philosopher[size];
        for (int i = 0; i < size; i++) {
            Chopstick left = chopsticks[leftOf(i)];
            Chopstick right = chopsticks[rightOf(i)];
            philosophers[i] = new Philosopher(i, left, right);
        }

        for (int i = 0; i < size; i++) {
            philosophers[i].start();
        }
    }

}