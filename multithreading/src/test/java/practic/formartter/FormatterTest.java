package practic.formartter;

import org.junit.Test;
import practic.TestThread;
import practic.threadlocalhandler.ThreadLocalSDF;
import practic.threads.ThreadFormat;

import java.text.SimpleDateFormat;

/**
 * Created by Игорь on 04.04.2018.
 */
public class FormatterTest {
    @Test
    public void testFormatter() {
        ThreadLocalSDF.set(new SimpleDateFormat());
        Formatter formatter = new Formatter();
        int numberThreads = 5;
        try {
            Thread[] threads = new Thread[numberThreads];
            for (int i = 0; i < numberThreads; i++) {
                threads[i] = new ThreadFormat("Formatter " + i,formatter);
            }
            TestThread.launchingAndStopThreads(threads);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

    }


}
