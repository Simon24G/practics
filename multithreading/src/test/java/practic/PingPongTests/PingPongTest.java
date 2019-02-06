package practic.PingPongTests;

import org.junit.Test;
import practic.PingPongSemaphore;
import practic.PingPongTask;
import practic.TestThread;
import practic.firstsecond.FirstSecond;
import practic.threads.Ping;
import practic.threads.Pong;
import practic.threads.ThreadWithAlternate;


/**
 * Created by Игорь on 01.04.2018.
 */
public class PingPongTest {
    @Test
    public void testPingPongWithCondition() {
        PingPongTask pp = new PingPongTask(true);
        Thread [] threads = new Thread[2];
        threads[0] = new Ping(pp);
        threads[1] = new Pong(pp);
        TestThread.launchingAndStopThreads(threads);
    }

    @Test
    public void testPingPongSimple() {
        ThreadWithAlternate[] threads =  new ThreadWithAlternate[2];
        threads[0] = new ThreadWithAlternate(FirstSecond.FIRST,"Ping");
        threads[1] = new ThreadWithAlternate(FirstSecond.SECOND,"Pong");
        ThreadWithAlternate.setupReferenceBetweenThreads(threads[0],threads[1]);
        TestThread.launchingAndStopThreads(threads);
    }

    @Test
    public void testPingPongSemaphore() {
        PingPongSemaphore pp = new PingPongSemaphore();
        Thread[] threads =  new Thread[2];
        threads[0] = new Ping(pp);
        threads[1] = new Pong(pp);
        TestThread.launchingAndStopThreads(threads);
    }
}