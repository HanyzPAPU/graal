package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_irem7 {
    public static int a = 2;

    public static int test3()  {
        return a % 13;
    }
}