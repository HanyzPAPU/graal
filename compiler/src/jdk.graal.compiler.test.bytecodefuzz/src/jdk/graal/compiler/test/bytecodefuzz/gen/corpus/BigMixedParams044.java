package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Assert;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class BigMixedParams044 {
    private static int choice = 4;
    private static int i0 = -1;
    private static int i1 = -1;
    private static int i2 = -1;
    private static int i3 = -1;
    private static double d1 = 1d;
    private static double d2 = 2d;
    private static boolean bo1 = true;
    private static boolean bo2 = false;
    private static byte by = (byte) -128;
    private static short sh = (short) -0x7FFF;
    private static char ch = (char) 0xFFFF;
    private static int in = -0x7FFFFFF;

    public static long test()  {
        switch (choice) {
            case 0:
                return bo1 ? 1L : 2L;
            case 1:
                return bo2 ? 1L : 2L;
            case 2:
                return by;
            case 3:
                return sh;
            case 4:
                return ch;
            case 5:
                return in;
        }
        return 42;
    }
}