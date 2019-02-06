package practic.threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Игорь on 24.03.2018.
 */
public class ThreadWD2 extends Thread {
    private int max;
    private int min;
    private int count;
    private List list;
    private boolean regim;
    public void ThreadWD2(ArrayList arrayList,int count, int max, int min, boolean regim){
        this.max = max;
        this.min = min;
        this.count = count;
        this.list = Collections.synchronizedList(arrayList);
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

            list.add(min+j);
        }
    }

    private void deleting(){
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            int j = random.nextInt(list.size());

            list.remove(j);
        }
    }

}
