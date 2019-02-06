package practic.threads;

import practic.formartter.Formatter;

import java.util.Date;

/**
 * Created by Игорь on 25.03.2018.
 */
public class ThreadFormat extends Thread {
    private Formatter formatter;

    public ThreadFormat(String name, Formatter formatter){
        super(name);
        this.formatter = formatter;
    }

    public void run(){
        for (int i = 0; i < 10 ; ++i){
            System.out.println(getName() + ": " + i + ")time = " + formatter.format(new Date()));
        }
    }
}