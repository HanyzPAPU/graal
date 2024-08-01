package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2c5 {
    public static int a = 65535;

    public static int testInt()  {
        return (char) a;
    }
}