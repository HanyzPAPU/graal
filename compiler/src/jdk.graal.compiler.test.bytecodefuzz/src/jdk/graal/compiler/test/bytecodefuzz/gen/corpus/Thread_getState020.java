package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Thread_getState020 {
    

    public static boolean test()  {
        return new Thread().getState() == Thread.State.NEW;
    }
}