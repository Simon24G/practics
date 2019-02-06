package practic.runnable;

import practic.producerconsumer.data.Data;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Игорь on 26.03.2018.
 */
public class Produser implements Runnable{

    private BlockingQueue<Data> queue;
    private int count;

    public Produser(BlockingQueue<Data> queue, int count) {
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
                queue.put(new Data(new int[3]));
                System.out.println("Producer added: String" + i + " Totally strings in queue = " + queue.size());
                Thread.sleep(200);
            }
            queue.put(null);
            System.out.println("Producer finished");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
