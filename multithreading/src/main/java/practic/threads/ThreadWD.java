package practic.threads;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Игорь on 24.03.2018.
 */
public class ThreadWD extends Thread {
    private int max;
    private int min;
    private int count;
    private ArrayList arrayList;
    private boolean regim;
    public void ThreadWD(ArrayList arrayList,int count, int max, int min, boolean regim){
        this.max = max;
        this.min = min;
        this.count = count;
        this.arrayList = arrayList;
        this.regim = regim;
    }

    public void run(){
        if(regim) {
            writing();
        } else {
            deleting();
        }
    }

    private void writing(){
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            int j = random.nextInt(max-min);
            synchronized (arrayList) {
                arrayList.add(min+j);
            }
        }
    }

    private void deleting(){
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            int j = random.nextInt(arrayList.size());
            synchronized (arrayList) {
                arrayList.remove(j);
            }
        }
    }

}
