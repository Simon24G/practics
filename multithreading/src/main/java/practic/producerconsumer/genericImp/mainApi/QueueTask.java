package thumbtack.producerconsumer.genericImp.mainApi;

import thumbtack.TestThread;
import thumbtack.producerconsumer.genericImp.functionalinterface.RunnableCreater;
import thumbtack.producerconsumer.genericImp.functionalinterface.RunnableExecute;
import thumbtack.producerconsumer.data.Task;
import thumbtack.producerconsumer.genericImp.regim.Regim;
import thumbtack.producerconsumer.genericImp.threads.Consumers;
import thumbtack.producerconsumer.genericImp.threads.Producers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Игорь on 27.03.2018.
 */
public class QueueTask {
    public static void launch(int numberProducers, int numberConsumers){
        if(numberProducers <= 0)
            return;
        if(numberConsumers <= 0)
            return;

        BlockingQueue<Task> queue = new LinkedBlockingQueue<>();
        RunnableCreater<Task> rCreater = (i) -> new Task("Im robot "+i);

        Task taskMarker = new Task(null);
        RunnableExecute<Task> rExecute = (e) -> {
            Regim r;
            if(e.getName() == null)
                return Regim.FINISH;
            else
                r = Regim.CONTINUE;
            e.execute();
            return r;
        };
        Thread [] threads = {
                new Producers<Task>(queue, rCreater, taskMarker, numberProducers),
                new Consumers<Task>(queue, rExecute, numberConsumers)
        };
        TestThread.launchingAndStopThreads(threads);
    }
}