package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class ThreadLocal020 {
    private static int i = 0;

    public static int test()  {
        ThreadLocal<Integer> local = new ThreadLocal<>();
        local.set(i + 5);
        return local.get();
    }
}