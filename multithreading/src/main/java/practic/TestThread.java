package practic;

/**
 * Created by Игорь on 24.03.2018.
 */
public class TestThread {
    public static void launchingAndStopThread(Thread thread){
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("Success");
    }

    public static void launchingAndStopThreads(Thread [] threads){
        for (int i=0; i< threads.length ;i++) {
            if (threads[i] == null)
                System.out.println("BAD"+i);
            threads[i].start();
        }

        try {
            for (int i=0; i< threads.length ;i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("Success");
    }
}
