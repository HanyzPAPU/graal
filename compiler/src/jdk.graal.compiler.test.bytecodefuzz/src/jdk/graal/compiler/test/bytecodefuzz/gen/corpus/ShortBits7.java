package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class ShortBits7 {
    public static short o = (short) 0xffff;

    public static short test()  {
        return Short.reverseBytes(o);
    }
}