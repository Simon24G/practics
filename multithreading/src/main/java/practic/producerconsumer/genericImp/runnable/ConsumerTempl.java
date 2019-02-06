package practic.producerconsumer.genericImp.runnable;

import practic.producerconsumer.genericImp.functionalinterface.RunnableExecute;
import practic.producerconsumer.genericImp.regim.Regim;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Игорь on 26.03.2018.
 */
public class ConsumerTempl<T> implements Runnable {

    private BlockingQueue<T> queue;
    private RunnableExecute<T> rExecute;

    public ConsumerTempl(BlockingQueue<T> queue, RunnableExecute<T> rExecute) {
        if(queue == null)
            throw new NullPointerException("failQueue");

        this.queue = queue;
        this.rExecute = rExecute;
    }

    public void run() {
        try {
            while (true) {
                if(queue.size() == 0)
                    continue;
                T element = queue.poll();
                boolean flag = queue.size()==0;

                if(element==null)
                    continue;
                Regim check = rExecute.execute(element);

                if (check == Regim.ADD) {
                    queue.put(element);
                }
                else if (check == Regim.FINISH) {
                    if(flag) {
                        queue.put(element);
                        break;
                    }
                    queue.put(element);
                }
                Thread.sleep(200);
            }
            System.out.println("Consumer finished working");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
