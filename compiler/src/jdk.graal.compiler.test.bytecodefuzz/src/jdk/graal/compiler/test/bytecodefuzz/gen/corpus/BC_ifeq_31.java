package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifeq_31 {
    public static int a = 1;

    public static boolean test()  {
        return a != 0;
    }
}