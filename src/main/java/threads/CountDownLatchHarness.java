package threads;

import java.util.concurrent.CountDownLatch;

/**
 * Creates a number of threads that run a given task concurrently. It uses
 two latches, a “starting gate” and an “ending gate”. The starting gate is initialized
 with a count of one; the ending gate is initialized with a count equal to the number
 of worker threads. The first thing each worker thread does is wait on the starting
 gate; this ensures that none of them starts working until they all are ready to start.
 The last thing each does is count down on the ending gate; this allows the master
 thread to wait efficiently until the last of the worker threads has finished, so it can
 calculate the elapsed time.
 */
public class CountDownLatchHarness {

    public long timeTasks(int nThreads, final Runnable task)
            throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            new Thread(() -> {
                try {
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException ignored) { }
            }).start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end-start;
    }
}