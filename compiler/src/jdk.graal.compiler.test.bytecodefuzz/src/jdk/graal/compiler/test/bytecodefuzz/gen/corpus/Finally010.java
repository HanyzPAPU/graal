package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Finally010 {
    public static int arg = 0;

    public static int test()  {
        try {
            return 0;
        } finally {
            return -1;
        }
    }
}