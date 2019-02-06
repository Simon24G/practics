package practic.formartter;

import practic.threadlocalhandler.ThreadLocalSDF;
import java.util.Date;

/**
 * Created by Игорь on 25.03.2018.
 */
public class Formatter {
    public String format(Date date) {
        if (date == null)
            throw new NullPointerException("fail");

        return ThreadLocalSDF.get().format(date);
    }
}
