package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Cmov021 {
    public static double a = 1.0;
    public static double b = -1.1;
    public static int v1 = 1;
    public static int v2 = 2;

    public static int test()  {
        return a < b ? v1 : v2;
    }
}