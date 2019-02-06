package practic.producerconsumer.simpleImp.runnable;


import practic.producerconsumer.data.Data;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Игорь on 28.03.2018.
 */
public class ConsumerData implements Runnable {

    private BlockingQueue<Data> queue;

    public ConsumerData(BlockingQueue<Data> queue) {
        if(queue == null)
            throw new NullPointerException("failQueue");

        this.queue = queue;
    }

    public void run() {
        try {
            while (true) {
                if(queue.size() == 0) {
                    //Thread.sleep(50);
                    continue;
                }
                Data element = queue.poll();

                if(element==null)
                    continue;
                int [] a = element.get();

                if(a.equals(new int[]{-1})) {
                    queue.put(element);
                    break;
                }
                for (int j = 0; j < a.length; j++)
                    System.out.print(" " + a[j]);
                System.out.println();
                Thread.sleep(200);
            }
            System.out.println("Consumer finished working");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}