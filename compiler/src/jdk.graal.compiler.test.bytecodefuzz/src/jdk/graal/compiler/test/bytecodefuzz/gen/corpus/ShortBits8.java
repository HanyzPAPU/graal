package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class ShortBits8 {
    public static short o = (short) 0x3fff;

    public static short test()  {
        return Short.reverseBytes(o);
    }
}