package practic.threads;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Игорь on 24.03.2018.
 */
public class ThreadDeleter extends Thread {
    private int count;
    private ArrayList arrayList;

    public void ThreadDeleter(ArrayList arrayList,int count){
        this.count = count;
        this.arrayList = arrayList;
    }

    public void run(){
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            int j = random.nextInt(arrayList.size());
            synchronized (arrayList) {
                arrayList.remove(j);
            }
        }
    }
}
