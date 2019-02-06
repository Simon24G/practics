package practic.producerconsumer.simpleImp.threads;

import practic.TestThread;
import practic.producerconsumer.data.Data;
import practic.producerconsumer.simpleImp.runnable.ProducerData;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Игорь on 27.03.2018.
 */
public class ProducersData extends Thread {
    private BlockingQueue<Data> queue;
    private int n;

    public ProducersData(BlockingQueue<Data> queue, int n) {
        if(queue == null)
            throw new NullPointerException("failQueue");
        this.queue = queue;
        this.n = n;
    }

    public void run(){
        Thread [] producers = new Thread[n];
        for (int i=0; i < n ;i++){
            producers[i] = new Thread(new ProducerData(queue,i+1));
        }
        TestThread.launchingAndStopThreads(producers);
        int [] a = {-1};
        queue.add(new Data(a));
    }
}
