package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Assert;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class BigMixedParams040 {
    public static int choice = 0;
    public static int i0 = -1;
    public static int i1 = -1;
    public static int i2 = -1;
    public static int i3 = -1;
    public static double d1 = 1d;
    public static double d2 = 2d;
    public static boolean bo1 = true;
    public static boolean bo2 = false;
    public static byte by = (byte) -128;
    public static short sh = (short) -0x7FFF;
    public static char ch = (char) 0xFFFF;
    public static int in = -0x7FFFFFF;

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