package practic.runnable;

import practic.producerconsumer.data.Data;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Игорь on 26.03.2018.
 */
public class Consumer implements Runnable {

    private BlockingQueue<Data> queue;
    private int count;

    public Consumer(BlockingQueue<Data> queue, int count) {
        if(queue == null)
            throw new NullPointerException("failQueue");
        if(count < 0)
            throw new NullPointerException("failCount");

        this.queue = queue;
        this.count = count;
    }

    public void run() {
        try {
            int i = 0;
            while (true) {
                if(queue.size() == 0)
                    continue;
                Data d = queue.element();

                if(d.get().length != 0){
                    if(d.get()[0] == -1){
                        System.out.println("Consumer finished working");
                        return;
                    }
                }
                System.out.println("Consumer take: String" + i + " Totally strings in queue = " + queue.size());
                Thread.sleep(200);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
