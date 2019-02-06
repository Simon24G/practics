package practic.producerconsumer.simpleImp.mainApi;

import practic.TestThread;
import practic.producerconsumer.data.Data;
import practic.producerconsumer.simpleImp.threads.ConsumersData;
import practic.producerconsumer.simpleImp.threads.ProducersData;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Игорь on 27.03.2018.
 */
public class QueueDataSimple {
    public static void launch(int numberProducers, int numberConsumers){
        if(numberProducers <= 0)
            return;
        if(numberConsumers <= 0)
            return;

        BlockingQueue<Data> queue = new LinkedBlockingQueue<>();

        Thread [] threads = {
                new ProducersData(queue, numberProducers),
                new ConsumersData(queue, numberConsumers)
        };
        TestThread.launchingAndStopThreads(threads);
    }
}