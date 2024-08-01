package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_tableswitch46 {
    private static int a = -8;

    public static int test()  {
        switch (a) {
            case -5:
                return 55;
            case -4:
                return 44;
            case -3:
                return 33;
        }
        return 11;
    }
}