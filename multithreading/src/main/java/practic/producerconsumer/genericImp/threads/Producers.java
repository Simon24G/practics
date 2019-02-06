package practic.producerconsumer.genericImp.threads;

import practic.TestThread;
import practic.producerconsumer.genericImp.functionalinterface.RunnableCreater;
import practic.producerconsumer.genericImp.runnable.ProducerTempl;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Игорь on 27.03.2018.
 */
public class Producers<T extends Object> extends Thread {
    private BlockingQueue<T> queue;
    private RunnableCreater<T> rCreater;
    private T marker;
    private int n;

    public Producers(BlockingQueue<T> queue, RunnableCreater<T> rCreater, T marker , int n) {
        if(queue == null)
            throw new NullPointerException("failQueue");
        this.marker = marker;
        this.queue = queue;
        this.rCreater = rCreater;
        this.n = n;
    }

    public void run(){
        Thread [] producers = new Thread[n];
        for (int i=0; i < n ;i++){
            producers[i] = new Thread(new ProducerTempl<T>(queue,i+1,rCreater));
        }
        TestThread.launchingAndStopThreads(producers);

        queue.add(marker);
    }
}
