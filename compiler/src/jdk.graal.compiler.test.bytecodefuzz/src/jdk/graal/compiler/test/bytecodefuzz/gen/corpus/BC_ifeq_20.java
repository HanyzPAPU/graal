package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifeq_20 {
    public static int a = 0;

    public static boolean test()  {
        return a == 0;
    }
}