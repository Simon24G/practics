package practic.additionaltests;

import org.junit.Test;

import java.util.Date;

public class TestOptimization {
    @Test
    public void optimizeTest(){
        String em = "awd";
        boolean emb = em == "awd";
        float sum = 0;
        Date ds = new Date();
        for(int i =0; i < 17*17*17*17*17*17*17; i++) {
            //sum++;
            if(emb){
            //if(em == "1") {
                sum++;
            } else {
                sum++;
            }
        }
        Date de = new Date();
        System.out.println("dt = " + (de.getTime() - ds.getTime()));
        System.out.println("Sum = " + sum);
    }
}
