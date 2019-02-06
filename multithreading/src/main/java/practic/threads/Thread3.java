package practic.threads;

/**
 * Created by Игорь on 24.03.2018.
 */
public class Thread3 extends Thread {

    private String massage;

    public void Thread3(String massage){
        //super(massage);
        this.massage = massage;
    }

    public void run(){
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println(i + ") msg: " + massage);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted: " + this.getClass());
        }
    }
}
