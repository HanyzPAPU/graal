package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_irem14 {
    public static int a = Integer.MIN_VALUE;
    public static int b = Integer.MIN_VALUE;

    public static int test6()  {
        return (a - 0xFF) % (b - 0xFF);
    }
}