package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class BigLongParams028 {
    public static int choice = 8;
    public static long p0 = 1L;
    public static long p1 = 2L;
    public static long p2 = 3L;
    public static long p3 = 4L;
    public static long p4 = 5L;
    public static long p5 = 6L;
    public static long p6 = 7L;
    public static long p7 = -8L;
    public static long p8 = -9L;

    public static long test()  {
        switch (choice) {
            case 0:
                return p0;
            case 1:
                return p1;
            case 2:
                return p2;
            case 3:
                return p3;
            case 4:
                return p4;
            case 5:
                return p5;
            case 6:
                return p6;
            case 7:
                return p7;
            case 8:
                return p8;
        }
        return 42;
    }
}