package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Thread_setPriority011 {
    public static int i = 1;

    public static boolean test()  {
        final Thread currentThread = Thread.currentThread();
        final int prev = currentThread.getPriority();
        currentThread.setPriority(i);
        currentThread.setPriority(prev);
        return true;
    }
}