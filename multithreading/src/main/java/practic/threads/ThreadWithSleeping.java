package practic.threads;

/**
 * Created by Игорь on 24.03.2018.
 */
public class ThreadWithSleeping extends Thread {
    private String massage;

    public void ThreadWithSleeping(String massage){
        //super(massage);
        this.massage = massage;
    }

    public void run(){
        try {
            while (true) {
                System.out.println(massage);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted: " + this.getClass());
        }
    }
}
