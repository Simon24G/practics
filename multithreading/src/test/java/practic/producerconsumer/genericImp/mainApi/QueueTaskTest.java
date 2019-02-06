package practic.producerconsumer.genericImp.mainApi;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Игорь on 04.04.2018.
 */
public class QueueTaskTest {
    @Test
    public void testGeneralOperations(){
        try {
            QueueTask.launch(5, 6);
            //QueueTask.launch(2, 3);
            Assert.assertTrue(true);
        } catch (Exception e){
            System.out.println(e.getStackTrace().toString());
            Assert.fail();
        }
    }
}
