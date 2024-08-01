package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Thread_setPriority013 {
    private static int i = 10;

    public static boolean test()  {
        final Thread currentThread = Thread.currentThread();
        final int prev = currentThread.getPriority();
        currentThread.setPriority(i);
        currentThread.setPriority(prev);
        return true;
    }
}