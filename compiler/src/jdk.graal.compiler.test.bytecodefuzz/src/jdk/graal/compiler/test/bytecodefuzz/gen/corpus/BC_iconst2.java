package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iconst2 {
    public static int arg = 2;

    public static int test()  {
        if (arg == 0) {
            return 0;
        }
        if (arg == 1) {
            return 1;
        }
        if (arg == 2) {
            return 2;
        }
        if (arg == 3) {
            return 3;
        }
        if (arg == 4) {
            return 4;
        }
        if (arg == 5) {
            return 5;
        }
        return 375;
    }
}