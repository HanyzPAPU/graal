package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lookupswitch041 {
    public static int a = 1;

    public static int test()  {
        final int b = a + 8;
        final int c = b + 2;
        switch (c) {
            case 77:
                return 0;
            case 107:
                return 1;
            case 117:
                return 2;
            case 143:
                return 3;
            case 222:
                return 4;
            case -112:
                return 5;
        }
        return 42;
    }
}