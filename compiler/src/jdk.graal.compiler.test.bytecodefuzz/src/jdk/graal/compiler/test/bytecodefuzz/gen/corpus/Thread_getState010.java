package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Thread_getState010 {
    

    public static boolean test()  {
        return Thread.currentThread().getState() == Thread.State.RUNNABLE;
    }
}