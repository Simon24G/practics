package practic.threadlocalhandler;

import java.text.SimpleDateFormat;

/**
 * Created by Игорь on 25.03.2018.
 */
public class ThreadLocalSDF {
    static final ThreadLocal<SimpleDateFormat> threadLocal = new InheritableThreadLocal<>();

    public static SimpleDateFormat get() {
        return threadLocal.get();
    }

    public static void set(SimpleDateFormat sdf) {
        threadLocal.set(sdf);
    }

    public static void delete() {
        threadLocal.remove();
    }
}
