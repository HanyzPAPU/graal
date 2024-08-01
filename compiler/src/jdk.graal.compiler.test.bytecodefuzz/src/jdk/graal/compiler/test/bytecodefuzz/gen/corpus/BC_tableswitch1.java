package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_tableswitch1 {
    public static int a = 0;

    public static int test()  {
        switch (a) {
            case 0:
                return 10;
            case 1:
                return 20;
            case 2:
                return 30;
            case 4:
                return 40;
            case 5:
                return 50;
        }
        return 42;
    }
}