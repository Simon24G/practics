package practic.threads;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Игорь on 24.03.2018.
 */
public class ThreadWriter extends Thread {
    private int max;
    private int min;
    private int count;
    private ArrayList arrayList;

    public void ThreadDeleter(ArrayList arrayList,int count, int max, int min){
        this.max = max;
        this.min = min;
        this.count = count;
        this.arrayList = arrayList;
    }

    public void run(){
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            int j = random.nextInt(max-min);
            synchronized (arrayList) {
                arrayList.add(min+j);
            }
        }
    }
}