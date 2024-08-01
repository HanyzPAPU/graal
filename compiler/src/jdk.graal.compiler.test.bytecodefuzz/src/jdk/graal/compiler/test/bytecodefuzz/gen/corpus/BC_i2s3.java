package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2s3 {
    private static int a = 32768;

    public static short test()  {
        return (short) a;
    }
}