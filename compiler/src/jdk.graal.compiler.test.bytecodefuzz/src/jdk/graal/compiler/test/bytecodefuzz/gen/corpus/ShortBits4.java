package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class ShortBits4 {
    private static short o = (short) -1;

    public static short test()  {
        return Short.reverseBytes(o);
    }
}