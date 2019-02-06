package practic.producerconsumer.simpleImp.runnable;

import practic.producerconsumer.data.Data;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Игорь on 26.03.2018.
 */
public class ProducerData implements Runnable{

    private BlockingQueue<Data> queue;
    private int count;

    public ProducerData(BlockingQueue<Data> queue, int count) {
        if(queue == null){
            throw new NullPointerException("failQueue");
        }
        if(count < 0){
            throw new NullPointerException("failCount");
        }
        this.queue = queue;
        this.count = count;
    }

    public void run() {
        try {
            for (int i = 0; i < count; i++) {
                int [] a = new int[i];
                for (int j=0; j<i;i++)
                    a[j] = j;

                queue.put(new Data(a));
                Thread.sleep(200);
            }
            System.out.println("Producer finished");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
