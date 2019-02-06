package practic.producerconsumer.simpleImp.mainApi;

import practic.TestThread;
import practic.producerconsumer.data.Executable;
import practic.producerconsumer.data.Task;
import practic.producerconsumer.data.TaskManyStages;
import practic.producerconsumer.genericImp.functionalinterface.RunnableCreater;
import practic.producerconsumer.genericImp.functionalinterface.RunnableExecute;
import practic.producerconsumer.genericImp.regim.Regim;
import practic.producerconsumer.genericImp.threads.Consumers;
import practic.producerconsumer.genericImp.threads.Producers;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Игорь on 27.03.2018.
 */
public class QueueTaskManyStagesSimple {
    public static void launch(int numberProducers, int numberConsumers){
        if(numberProducers <= 0)
            return;
        if(numberConsumers <= 0)
            return;

        BlockingQueue<TaskManyStages> queue = new LinkedBlockingQueue<>();
            RunnableCreater<TaskManyStages> rCreater = (i) -> {
            List<Executable> tasks = new LinkedList<>();
            for (int j = 0; j < i+1; j++)
                tasks.add(new Task("I am hare Version_" + j));
            return new TaskManyStages("Im robot "+i,tasks);
        };

        TaskManyStages taskMarker = new TaskManyStages(null);

        RunnableExecute<TaskManyStages> rExecute = (e) -> {
            if(e.getName() == null)
                return Regim.FINISH;
            else {
                e.execute();
                if (e.getStatusFinish())
                    return Regim.CONTINUE;
                else
                    return Regim.ADD;
            }
        };

        Thread [] threads = {
                new Producers<>(queue, rCreater, taskMarker, numberProducers),
                new Consumers<>(queue, rExecute, numberConsumers)
        };
        TestThread.launchingAndStopThreads(threads);
    }
}