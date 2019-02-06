package practic.threads;

import practic.firstsecond.FirstSecond;

/**
 * Created by Игорь on 24.03.2018.
 */
public class ThreadWithAlternate extends Thread {
    private String massage;
    private volatile FirstSecond current;
    private FirstSecond thisCurr;
    private ThreadWithAlternate thread;

    public ThreadWithAlternate(FirstSecond thisCurr, String massage){
        this.massage = massage;
        this.thisCurr = thisCurr;
    }

    public void setThread(ThreadWithAlternate thread){
        this.thread = thread;
    }

    public void run(){
        int i=0;
        while (i<100) {
            if(check()) {
                System.out.println(massage);
                FirstSecond curr = (thisCurr == FirstSecond.FIRST) ? FirstSecond.SECOND : FirstSecond.FIRST;

                setCurrent(curr);
                thread.setCurrent(curr);
                i++;
            }
        }
    }

    public boolean check(){
        return (thisCurr == thread.getCurrent()) && (thisCurr == current);
    }

    public FirstSecond getCurrent(){
        return current;
    }
    public void setCurrent(FirstSecond current){
        this.current = current;
    }

    public static void setupReferenceBetweenThreads(ThreadWithAlternate first, ThreadWithAlternate second){
        first.setThread(second);
        second.setThread(first);
        first.setCurrent(FirstSecond.FIRST);
        second.setCurrent(FirstSecond.FIRST);
    }
}
