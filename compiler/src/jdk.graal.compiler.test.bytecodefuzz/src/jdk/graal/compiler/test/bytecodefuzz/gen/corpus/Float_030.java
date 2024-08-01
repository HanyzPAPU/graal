package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Float_030 {
    

    public static boolean test()  {
        return Float.floatToIntBits(Float.intBitsToFloat(0x7fc00088)) == 0x7fc00000;
    }
}