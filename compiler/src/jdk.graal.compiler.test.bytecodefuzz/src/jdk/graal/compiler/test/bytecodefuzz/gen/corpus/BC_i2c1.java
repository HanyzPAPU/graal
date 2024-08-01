package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2c1 {
    public static int a = 645;

    public static char test()  {
        return (char) a;
    }
}