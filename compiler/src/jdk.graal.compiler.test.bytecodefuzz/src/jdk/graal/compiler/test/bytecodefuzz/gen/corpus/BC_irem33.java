package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_irem33 {
    public static int a = -2147483648;

    public static int test()  {
        return a % 1;
    }
}