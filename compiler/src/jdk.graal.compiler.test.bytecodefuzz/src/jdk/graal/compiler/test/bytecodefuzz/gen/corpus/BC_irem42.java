package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_irem42 {
    public static int a = -8;

    public static int test()  {
        return a % 8;
    }
}