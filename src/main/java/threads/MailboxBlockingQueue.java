package threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class MailboxBlockingQueue {
    private BlockingQueue<String> mailQueue;

    public MailboxBlockingQueue(int bound) {
        mailQueue = new LinkedBlockingQueue<>(bound);
    }

    // Note: no need of synchronized methods
    public void put(String mail) {
        try {
            System.out.println("PUT : " + mail);
            mailQueue.put(mail);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public String take() {
        String mail = null;
        try {
            mail = mailQueue.take();
            System.out.println("TAKE : " + mail);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        return mail;
    }

    public static void main(String args[]) {

        // 1. Create a shared resource. It should be final for anonymous runnable tasks
        final MailboxBlockingQueue mb = new MailboxBlockingQueue(10);

        // 2. Create a Producer and Consumer threads
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    mb.put("Hello World " + i);
                }
            }
        });

        // consumer tread call take() interface
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mb.take();
                }
            }
        });

        // 3. Start the threads
        t1.start();
        t2.start();

    }

}


///**
// * Postman Class in java.
// */
//class Producer implements Runnable {
//
//    private final BlockingQueue<Integer> sharedQueue;
//
//    public Producer(BlockingQueue<Integer> sharedQueue) {
//        this.sharedQueue = sharedQueue;
//    }
//
//    @Override
//    public void run() {
//        for(int i=0; i< 20; i++){
//            try {
//                System.out.println("Produced : " + i);
//                //put/produce into mailQueue.
//                sharedQueue.put(i);
//            } catch (InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
//        }
//    }
//
//}


// class Consumer implements Runnable{
//
//    private BlockingQueue<Integer> sharedQueue;
//
//    public Consumer (BlockingQueue<Integer> sharedQueue) {
//        this.sharedQueue = sharedQueue;
//    }
//
//    @Override
//    public void run() {
//        while(true){
//            try {
//                //take/consume from mailQueue.
//                System.out.println("CONSUMED : "+ sharedQueue.take());
//            } catch (InterruptedException ex) {
//
//            }
//        }
//    }
//
//
//}


