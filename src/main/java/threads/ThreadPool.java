package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    private BlockingQueue<Runnable> taskQueue = null;
    private List<WorkerThread> workerPool = null;
    private boolean isStopped = false;

    public ThreadPool(int noOfThreads, int maxNoOfTasks){
        taskQueue = new LinkedBlockingQueue(maxNoOfTasks);
        workerPool = new ArrayList<>(noOfThreads);

        for(int i=0; i<noOfThreads; i++){
            workerPool.add(new WorkerThread(taskQueue));
        }
        for(WorkerThread thread : workerPool){
            thread.start();
        }
    }

    public synchronized void  execute(Runnable task) throws Exception{
        if(this.isStopped) throw
                new IllegalStateException("ThreadPool is stopped");

        this.taskQueue.put(task);
    }

    public synchronized void stop(){
        this.isStopped = true;
        for(WorkerThread thread : workerPool){
            thread.doStop();
        }
    }

}

 class WorkerThread extends Thread {

    private BlockingQueue<Runnable> taskQueue = null;
    private boolean       isStopped = false;

    public WorkerThread(BlockingQueue<Runnable> queue){
        taskQueue = queue;
    }

    public void run(){
        while(!isStopped()){
            try{
                Runnable task =  taskQueue.take();
                task.run();
            } catch(Exception e){
                //log or otherwise report exception,
                //but keep pool thread alive.
            }
        }
    }

    public synchronized void doStop(){
        isStopped = true;
        this.interrupt(); //break pool thread out of dequeue() call.
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }
}

