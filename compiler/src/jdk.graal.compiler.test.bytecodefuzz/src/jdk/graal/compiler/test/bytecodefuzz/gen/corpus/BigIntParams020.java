package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class BigIntParams020 {
    private static int choice = 0;
    private static int p0 = 1;
    private static int p1 = 2;
    private static int p2 = 3;
    private static int p3 = 4;
    private static int p4 = 5;
    private static int p5 = 6;
    private static int p6 = 7;
    private static int p7 = -8;
    private static int p8 = -9;

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