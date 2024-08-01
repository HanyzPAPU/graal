package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2c2 {
    public static int a = 65535;

    public static char test()  {
        return (char) a;
    }
}