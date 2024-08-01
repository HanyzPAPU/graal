package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
public class Thread_sleep012 {
    private static int i = 100;

    public static boolean test()  throws InterruptedException {
        final long before = System.currentTimeMillis();
        Thread.sleep(i);
        return System.currentTimeMillis() - before >= i;
    }
}