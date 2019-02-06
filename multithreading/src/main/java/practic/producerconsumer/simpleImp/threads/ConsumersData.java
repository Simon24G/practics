package practic.producerconsumer.simpleImp.threads;

import practic.TestThread;
import practic.producerconsumer.data.Data;
import practic.producerconsumer.simpleImp.runnable.ConsumerData;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Игорь on 27.03.2018.
 */
public class ConsumersData extends Thread {
    private BlockingQueue<Data> queue;
    private int n;

    public ConsumersData(BlockingQueue<Data> queue, int n) {
        if(queue == null)
            throw new NullPointerException("failQueue");

        this.queue = queue;
        this.n = n;
    }

    public void run(){
        Thread [] consumers = new Thread[n];
        for (int i=0; i < n ;i++){
            consumers[i] = new Thread(new ConsumerData(queue));
        }
        TestThread.launchingAndStopThreads(consumers);
    }
}
