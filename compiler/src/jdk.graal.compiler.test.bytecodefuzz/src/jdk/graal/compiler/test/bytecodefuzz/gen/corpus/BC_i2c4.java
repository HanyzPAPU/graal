package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2c4 {
    private static int a = 645;

    public static int testInt()  {
        return (char) a;
    }
}