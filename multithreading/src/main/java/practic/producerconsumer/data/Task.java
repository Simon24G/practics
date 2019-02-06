package practic.producerconsumer.data;

/**
 * Created by Игорь on 27.03.2018.
 */
public class Task implements Executable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void execute() {
        System.out.println("<=============");
        System.out.println("task \""+name+"\" execute:");
        System.out.println("=============>");
    }

    public String getName(){
        return name;
    }
}
