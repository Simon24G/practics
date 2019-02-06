package practic.threads;

import practic.pingponginter.PingPong;

/**
 * Created by Игорь on 24.03.2018.
 */
public class Pong extends Thread {
    private PingPong pingPong;

    public Pong(PingPong pingPong) {
        this.pingPong = pingPong;
    }

    public void run(){
        int i=0;
        while (i<100){
            pingPong.pong();
            i++;
        }
    }
}
