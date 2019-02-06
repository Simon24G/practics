package practic;

import practic.pingponginter.PingPong;

import java.util.concurrent.Semaphore;

/**
 * Created by Игорь on 03.04.2018.
 */
public class PingPongSemaphore implements PingPong {
    private Semaphore ping = new Semaphore(0);
    private Semaphore pong = new Semaphore(1);

    public PingPongSemaphore(){
    }

    @Override
    public void ping() {
        try {
            pong.acquire();
            System.out.println("Ping");
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        } finally {
            ping.release();
        }
    }

    @Override
    public void pong() {
        try {
            ping.acquire();
            System.out.println("Pong");
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        } finally {
            pong.release();
        }
    }
}
