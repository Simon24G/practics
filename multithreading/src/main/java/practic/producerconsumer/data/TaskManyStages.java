package practic.producerconsumer.data;

import java.util.List;

/**
 * Created by Игорь on 27.03.2018.
 */
public class TaskManyStages {

    private List<Executable> stages;
    private int currentStage = 0;
    private String name;

    public TaskManyStages(String name) {
        this.name = name;
    }

    public TaskManyStages(String name, List<Executable> stages) {
        this.name = name;
        this.stages = stages;
    }

    public void execute(){
        if(stages == null) {
            System.out.println("task \""+getName()+"\" has no stages!");
            return;
        }
        if (currentStage > stages.size()){
            System.out.println("task \""+getName()+"\" - error: executing finished task!" + stages.size());
            return;
        }

        System.out.println("task \""+getName()+"\" | stage \"" + currentStage + "\" execute:");
        currentStage++;
    }

    public boolean getStatusFinish(){
        if(stages == null)
            return true;
        return currentStage == stages.size();
    }

    public String getName(){
        return name;
    }
}
