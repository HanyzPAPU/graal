package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2b4 {
    public static int a = -1;

    public static int testInt()  {
        return (byte) a;
    }
}