package practic;

import practic.pingponginter.PingPong;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Игорь on 24.03.2018.
 */
public class PingPongTask implements PingPong{

    private Lock lock = new ReentrantLock();
    private Condition workPing = lock.newCondition();
    private Condition workPong = lock.newCondition();
    private boolean currentPing;

    public PingPongTask(boolean currentPing){
        this.currentPing = currentPing;
    }

    @Override
    public void ping(){
        lock.lock();
        try {
            while (!currentPing)
                workPing.await();
            System.out.println("Ping");
            currentPing = false;
            workPong.signal();
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void pong(){
        lock.lock();
        try {
            while (currentPing)
                workPong.await();
            System.out.println("Pong");
            currentPing = true;
            workPing.signal();
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        } finally {
            lock.unlock();
        }
    }
}
