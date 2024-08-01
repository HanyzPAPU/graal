package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class BigFloatParams026 {
    private static int choice = 6;
    private static float p0 = 1f;
    private static float p1 = 2f;
    private static float p2 = 3f;
    private static float p3 = 4f;
    private static float p4 = 5f;
    private static float p5 = 6f;
    private static float p6 = 7f;
    private static float p7 = 8f;
    private static float p8 = 9f;

    public static float test()  {
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