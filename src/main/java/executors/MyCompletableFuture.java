package executors;

import java.net.ConnectException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class MyCompletableFuture {

    private static volatile boolean registered;
    private ScheduledExecutorService connectExecutor= Executors.newSingleThreadScheduledExecutor();
    private final AtomicReference<ScheduledFuture<?>> schFutureRef = new AtomicReference<>(null);
    private final AtomicReference<CompletableFuture<?>> comFutureRef = new AtomicReference<>(null);
    private int count = 0 ;


    public void connectAsync(long initialDelay, long period, ConnectSuccess callback) {
        System.out.println("Composing async operations with CompletableFuture");
        CompletableFuture<Void> completableFuture = CompletableFuture
                .runAsync(() -> scheduledConnect(initialDelay, period))
                .thenAccept(callback::handle);

        // First time comFutureRef will have null. If called again, the cancels previous future.
        Future<?> prevFuture = comFutureRef.getAndSet(completableFuture);
        cancelFuture(prevFuture);
        System.out.println("connectAsync - Returns");
    }

    private void scheduledConnect(long initialDelay, long period) {
        System.out.println("scheduledConnect - Scheduling connect for every 3 sec");
        ScheduledFuture<?> schFuture = connectExecutor.scheduleAtFixedRate(
                this::connect, initialDelay, period, TimeUnit.SECONDS);

        Future<?> prevFuture = schFutureRef.getAndSet(schFuture);
        cancelFuture(prevFuture);

        // Waits for cancellation of the future
        try {
            schFuture.get();
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        } catch (CancellationException e) {
            System.out.println("CancellationException on scheduled connect, so connected to the Server");
        }

    }

    public void connect() {
        try {
            // do sth
            if(count++ < 5) throw new ConnectException("Connection Refused");
            // Connected
            cancelFuture(schFutureRef.get());
        } catch (Exception e) {
            System.out.println("Could not connect - "+ e.getMessage());
        }
    }

    public static void main(String[] args) {
        MyCompletableFuture c = new MyCompletableFuture();
        System.out.println("Main Thread - registering consumers");
        c.connectAsync(3,3, MyCompletableFuture::registerConsumers);
        System.out.println("Main thread - continues");
        while (!registered){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void registerConsumers(Void v){
        System.out.println("Registered the consumer after successful connection.");
        registered = true;
    }

    private void cancelFuture(Future<?> future) {
        if (future != null) {
            future.cancel(true);
        }
    }
}
