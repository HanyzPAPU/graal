package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_irem2 {
    public static int a = 256;
    public static int b = 4;

    public static int test()  {
        return a % b;
    }
}