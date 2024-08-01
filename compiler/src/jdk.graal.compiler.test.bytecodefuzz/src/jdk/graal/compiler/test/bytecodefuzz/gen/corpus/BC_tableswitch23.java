package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_tableswitch23 {
    private static int a = 5;

    public static int test()  {
        switch (a) {
            case 5:
                return 55;
            case 6:
                return 66;
            case 7:
                return 77;
        }
        return 11;
    }
}