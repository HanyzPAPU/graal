package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lookupswitch026 {
    public static int a = 97;

    public static int test()  {
        final int b = a;
        switch (b) {
            case 67:
                return 0;
            case 97:
                return 1;
            case 107:
                return 2;
            case 133:
                return 3;
            case 212:
                return 4;
            case -122:
                return 5;
        }
        return 42;
    }
}