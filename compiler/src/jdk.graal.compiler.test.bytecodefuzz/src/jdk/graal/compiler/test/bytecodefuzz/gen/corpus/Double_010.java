package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Double_010 {
    

    public static boolean test()  {
        return Double.doubleToLongBits(Double.longBitsToDouble(0x7ff8000000000088L)) == 0x7ff8000000000000L;
    }
}