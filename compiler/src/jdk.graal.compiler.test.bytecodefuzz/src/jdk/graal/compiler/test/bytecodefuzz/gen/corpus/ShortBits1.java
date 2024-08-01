package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class ShortBits1 {
    public static short o = (short) 0x1708L;

    public static short test()  {
        return Short.reverseBytes(o);
    }
}