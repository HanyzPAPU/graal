package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class BigDoubleParams023 {
    public static int choice = 3;
    public static double p0 = 1d;
    public static double p1 = 2d;
    public static double p2 = 3d;
    public static double p3 = 4d;
    public static double p4 = 5d;
    public static double p5 = 6d;
    public static double p6 = 7d;
    public static double p7 = 8d;
    public static double p8 = 9d;

    public static double test()  {
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