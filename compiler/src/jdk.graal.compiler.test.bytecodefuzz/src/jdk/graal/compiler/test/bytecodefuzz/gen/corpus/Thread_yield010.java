package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
public class Thread_yield010 {
    

    public static boolean test()  {
        Thread.yield();
        return true;
    }
}