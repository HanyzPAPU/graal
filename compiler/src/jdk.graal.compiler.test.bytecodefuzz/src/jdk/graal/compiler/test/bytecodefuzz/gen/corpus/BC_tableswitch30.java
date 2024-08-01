package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_tableswitch30 {
    public static int a = -1;

    public static int test()  {
        switch (a) {
            case -2:
                return 22;
            case -1:
                return 11;
            case 0:
                return 33;
            case 1:
                return 77;
        }
        return 99;
    }
}