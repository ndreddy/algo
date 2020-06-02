package algo.queues;

import java.util.LinkedList;
import java.util.Queue;

public class MyBlockingQueue<T> {

    private Queue<T> queue = new LinkedList<T>();
    private int  limit = 10;

    public MyBlockingQueue(int limit){
        this.limit = limit;
    }


    public synchronized void put(T t)
            throws InterruptedException  {
        while(this.queue.size() == this.limit) {
            System.out.println(Thread.currentThread().getId() + "Waiting");
            wait();
        }
        this.queue.add(t);

        if(this.queue.size() == 0) {
            System.out.println(Thread.currentThread().getId() + "notifyall");
            notifyAll();
        }

    }


    public synchronized T take()
            throws InterruptedException{
        while(this.queue.size() == 0){
            System.out.println(Thread.currentThread().getId() + "Waiting");
            wait();
        }
        T t = this.queue.poll();
        if(this.queue.size() == this.limit){
            System.out.println(Thread.currentThread().getId() + "notifyAll");
            notifyAll();
        }
        return t;
    }

    public static void main(String[] args) {
       final MyBlockingQueue<String> bq = new MyBlockingQueue<>(10);

       // producer thread
        new Thread(() -> {
            try {
                for (int i =0; i<20; i++) {
                    bq.put("hello world");
                    System.out.println(Thread.currentThread().getId() + "Put: hello world " + i);
                }
            } catch (InterruptedException e) { }
        }).start();

        // consumer thread
        new Thread(() -> {
            try {
                while (true) {
                    String str = bq.take();
                    System.out.println(Thread.currentThread().getId() + "Take:" + str);
                }
            } catch (InterruptedException e) { }
        }).start();

    }
}