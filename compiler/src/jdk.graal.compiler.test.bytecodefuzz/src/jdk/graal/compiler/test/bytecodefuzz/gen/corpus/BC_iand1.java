package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iand1 {
    public static int a = 0;
    public static int b = -1;

    public static int test()  {
        return a & b;
    }
}