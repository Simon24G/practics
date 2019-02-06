package practic.threads;

import practic.pingponginter.PingPong;

/**
 * Created by Игорь on 24.03.2018.
 */
public class Ping extends Thread {
    private PingPong pingPong;

    public Ping(PingPong pingPong) {
        this.pingPong = pingPong;
    }

    public void run(){
        int i=0;
        while (i<100){
            pingPong.ping();
            i++;
        }
    }
}
