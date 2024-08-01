package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class ThreadLocal022 {
    public static int i = 2;

    public static int test()  {
        ThreadLocal<Integer> local = new ThreadLocal<>();
        local.set(i + 5);
        return local.get();
    }
}