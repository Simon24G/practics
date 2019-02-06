package practic.producerconsumer.genericImp.threads;

import practic.TestThread;
import practic.producerconsumer.genericImp.runnable.ConsumerTempl;
import practic.producerconsumer.genericImp.functionalinterface.RunnableExecute;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Игорь on 27.03.2018.
 */
public class Consumers<T extends Object> extends Thread {
    private BlockingQueue<T> queue;
    private RunnableExecute<T> rExecute;
    private int n;

    public Consumers(BlockingQueue<T> queue, RunnableExecute<T> rExecute, int n) {
        if(queue == null)
            throw new NullPointerException("failQueue");

        this.queue = queue;
        this.rExecute = rExecute;
        this.n = n;
    }

    public void run(){
        Thread [] consumers = new Thread[n];
        for (int i=0; i < n ;i++){
            consumers[i] = new Thread(new ConsumerTempl<>(queue,rExecute));
        }
        TestThread.launchingAndStopThreads(consumers);
    }
}
