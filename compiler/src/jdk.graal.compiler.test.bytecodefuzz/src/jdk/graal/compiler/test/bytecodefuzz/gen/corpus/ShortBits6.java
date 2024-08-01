package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class ShortBits6 {
    public static short o = (short) 0xff00;

    public static short test()  {
        return Short.reverseBytes(o);
    }
}