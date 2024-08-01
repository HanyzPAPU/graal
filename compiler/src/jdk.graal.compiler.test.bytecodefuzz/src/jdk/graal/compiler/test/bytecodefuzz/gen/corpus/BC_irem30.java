package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_irem30 {
    public static int a = -1;

    public static int test()  {
        return a % 1;
    }
}