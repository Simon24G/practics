package practic.producerconsumer.genericImp.mainApi;

import practic.TestThread;
import practic.producerconsumer.data.Data;
import practic.producerconsumer.genericImp.functionalinterface.RunnableCreater;
import practic.producerconsumer.genericImp.functionalinterface.RunnableExecute;
import practic.producerconsumer.genericImp.regim.Regim;
import practic.producerconsumer.genericImp.threads.Consumers;
import practic.producerconsumer.genericImp.threads.Producers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Игорь on 27.03.2018.
 */
public class QueueData {
    public static void launch(int numberProducers, int numberConsumers){
        if(numberProducers <= 0)
            return;
        if(numberConsumers <= 0)
            return;

        BlockingQueue<Data> queue = new LinkedBlockingQueue<>();

        RunnableCreater<Data> rCreater = (i) -> {
            int [] a = new int[i];
            for (int j=0; j<i;i++)
                a[j] = j;

            return new Data(a);
        };
        int [] marker = new int[1];
        marker[0] = -1;
        Data dataMarker = new Data(marker);
        RunnableExecute<Data> rExecute = (e) -> {
            int [] a = e.get();
            for (int j = 0; j < a.length; j++)
                System.out.print(" " + (a[j]+1));
            System.out.println();
            if(a.equals(marker))
                return Regim.FINISH;
            else
                return Regim.CONTINUE;
        };
        Thread [] threads = {
                new Producers<>(queue, rCreater, dataMarker, numberProducers),
                new Consumers<>(queue, rExecute, numberConsumers)
        };
        TestThread.launchingAndStopThreads(threads);
    }
}