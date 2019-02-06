package practic.runnable;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by Игорь on 25.03.2018.
 */
public class RunnableWriter implements Runnable{
    private String massage;
    private ReadWriteLock lock;

    public RunnableWriter(String massage, ReadWriteLock lock) {
        this.massage = massage;
        this.lock = lock;
    }

    public void run() {
        lock.writeLock().lock();
        try {
            System.out.println(massage);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
