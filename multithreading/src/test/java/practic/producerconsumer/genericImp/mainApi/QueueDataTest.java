package practic.producerconsumer.genericImp.mainApi;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Игорь on 04.04.2018.
 */
public class QueueDataTest {
    @Test
    public void testGeneralOperations(){
        try {
            QueueData.launch(5, 6);
            Assert.assertTrue(true);
        } catch (Exception e){
            System.out.println(e.getStackTrace().toString());
            Assert.fail();
        }
    }
}
