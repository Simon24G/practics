package practic.runnable;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by Игорь on 25.03.2018.
 */
public class RunnableReader implements Runnable{
    private String massage;
    private ReadWriteLock lock;

    public RunnableReader(String massage, ReadWriteLock lock) {
        this.massage = massage;
        this.lock = lock;
    }

    public void run() {
        lock.readLock().lock();
        try {
            System.out.println(massage);
        } finally {
            lock.readLock().unlock();
        }
    }
}
