package practic;

/**
 * Hello world!
 *
 */
public class PrintThread
{
    public static void main( String[] args )
    {
        Thread thread = new Thread("Boss");
        System.out.println( "thread = " + thread);
        System.out.println( "MAX_PRIORITY = " + thread.MAX_PRIORITY);
        System.out.println( "MIN_PRIORITY = " + thread.MIN_PRIORITY);
        System.out.println( "NORM_PRIORITY = " + thread.NORM_PRIORITY);
        System.out.println( "name = " + thread.getName());
        System.out.println( "toString() = " + thread.toString());
        System.out.println( "getClass() = " + thread.getClass());
    }
}
