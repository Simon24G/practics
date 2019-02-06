package practic.producerconsumer.genericImp.runnable;

import practic.producerconsumer.genericImp.functionalinterface.RunnableCreater;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Игорь on 26.03.2018.
 */
public class ProducerTempl<T> implements Runnable{

    private BlockingQueue<T> queue;
    private int count;
    private RunnableCreater<T> rCreater;


    public ProducerTempl(BlockingQueue<T> queue, int count, RunnableCreater<T> rCreater) {
        if(queue == null){
            throw new NullPointerException("failQueue");
        }
        if(count < 0){
            throw new NullPointerException("failCount");
        }
        this.queue = queue;
        this.count = count;
        this.rCreater = rCreater;
    }

    public void run() {
        try {
            for (int i = 0; i < 10*count; i++) {
                queue.put(rCreater.create(i));
                Thread.sleep(200);
            }
            System.out.println("Producer finished");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
