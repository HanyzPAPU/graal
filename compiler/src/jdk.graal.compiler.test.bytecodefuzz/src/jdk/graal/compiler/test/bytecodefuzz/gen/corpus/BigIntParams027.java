package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class BigIntParams027 {
    public static int choice = 7;
    public static int p0 = 1;
    public static int p1 = 2;
    public static int p2 = 3;
    public static int p3 = 4;
    public static int p4 = 5;
    public static int p5 = 6;
    public static int p6 = 7;
    public static int p7 = -8;
    public static int p8 = -9;

    public static int test()  {
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